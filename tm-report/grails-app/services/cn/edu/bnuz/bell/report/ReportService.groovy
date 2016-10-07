package cn.edu.bnuz.bell.report

import grails.core.GrailsApplication
import grails.transaction.Transactional
import grails.web.context.ServletContextHolder
import org.eclipse.birt.core.framework.Platform
import org.eclipse.birt.core.framework.PlatformServletContext
import org.eclipse.birt.report.engine.api.*
import org.eclipse.birt.report.engine.api.impl.ScalarParameterDefn
import org.hibernate.SessionFactory
import org.hibernate.internal.SessionImpl
import org.springframework.beans.factory.annotation.Value

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Transactional
class ReportService {
    IReportEngine reportEngine
    SessionFactory sessionFactory

    @Value('${bell.report.design.dir}')
    String inputDir;


    @PostConstruct
    private void init() {
        log.debug('Report Service Init')
        EngineConfig config = new EngineConfig()
        config.setPlatformContext(new PlatformServletContext(ServletContextHolder.servletContext))

        Platform.startup(config)
        IReportEngineFactory factory = (IReportEngineFactory)Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY)
        reportEngine = factory.createReportEngine(config)
    }

    @PreDestroy
    private void shutdown() {
        log.debug('Report Service Shutdown')
        reportEngine.destroy()
        Platform.shutdown()
    }

    def runAndRender(ReportCommand cmd) {
        String designFile = new File(inputDir, "${cmd.reportDesign}.rptdesign")
        IReportRunnable reportDesign = reportEngine.openReportDesign(designFile)
        cmd.title = reportDesign.getProperty(IReportRunnable.TITLE)

        IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(reportDesign)
        runAndRenderTask.appContext.put('OdaJDBCDriverPassInConnection', ((SessionImpl)sessionFactory.currentSession).connection())
        runAndRenderTask.appContext.put('OdaJDBCDriverPassInConnectionCloseAfterUse', false)

        IGetParameterDefinitionTask getParameterDefinitionTask = reportEngine.createGetParameterDefinitionTask(reportDesign)
        Collection parameterDefinitions = getParameterDefinitionTask.getParameterDefns(true)
        runAndRenderTask.setParameterValues(processParameters(parameterDefinitions, cmd.parameters))

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        RenderOption renderOption = cmd.renderOption
        renderOption.setOutputStream(outputStream)

        runAndRenderTask.setRenderOption(renderOption)
        runAndRenderTask.run()
        runAndRenderTask.close()

        return outputStream
    }

    private Map<String, Object> processParameters(Collection parameterDefinitions, Map<String, String> parameters) {
        def reportParameters = [:]
        parameterDefinitions.each { IParameterDefnBase parameterDefinition ->
            if (parameterDefinition instanceof IScalarParameterDefn) {
                def definition = parameterDefinition as IScalarParameterDefn
                switch (definition.dataType) {
                    case IParameterDefn.TYPE_DECIMAL:
                        reportParameters[definition.name] = new BigDecimal(parameters[definition.name])
                        break
                    case IParameterDefn.TYPE_INTEGER:
                        reportParameters[definition.name] = new Integer(parameters[definition.name])
                        break
                    default:
                        reportParameters[definition.name] = parameters[definition.name]
                        break
                }
            }
        }
        return reportParameters
    }
}
