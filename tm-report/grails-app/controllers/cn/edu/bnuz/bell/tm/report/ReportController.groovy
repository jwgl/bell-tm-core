package cn.edu.bnuz.bell.tm.report

import cn.edu.bnuz.bell.report.ReportCommand
import cn.edu.bnuz.bell.report.ReportService

class ReportController {
    ReportService reportService
    def index() { render 'ok'}

    def show(String id) {
        ReportCommand cmd = new ReportCommand(
                reportDesign: id,
                format: params.format,
                parameters: params.findAll {key, value ->
                    !['id', 'controller', 'action', 'format'].contains(key)
                }
        )

        def outputStream = reportService.runAndRender(cmd)
        response.setHeader('X-Report-Title', URLEncoder.encode(cmd.title, 'UTF-8'))
        response.setContentType(cmd.contentType)
        response.outputStream << outputStream.toByteArray()
    }
}
