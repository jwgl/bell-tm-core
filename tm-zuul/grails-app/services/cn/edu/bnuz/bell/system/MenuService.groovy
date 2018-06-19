package cn.edu.bnuz.bell.system

import cn.edu.bnuz.bell.orm.PostgreSQLStringArrayUserType
import cn.edu.bnuz.bell.security.SecurityService
import com.netflix.discovery.DiscoveryClient
import org.hibernate.Session
import org.springframework.cloud.client.discovery.event.HeartbeatEvent
import org.springframework.context.ApplicationListener

import javax.persistence.ParameterMode
import javax.persistence.StoredProcedureQuery

class MenuService implements ApplicationListener<HeartbeatEvent>  {
    SecurityService securityService
    Set<String> applicationNames

    def getUserMenus() {
        MenuItem.withNewSession { Session session ->
            def procedure = session.createStoredProcedureQuery('sp_build_menu')
            procedure.registerStoredProcedureParameter(1, String, ParameterMode.OUT)
            procedure.registerStoredProcedureParameter(2, PostgreSQLStringArrayUserType, ParameterMode.IN)
            println securityService.getUserRoles()
            procedure.setParameter(2, securityService.getUserRoles() as String[])
            procedure.execute()
            return procedure.getOutputParameterValue(1)
        }
    }

    def updateMenuItem() {
        MenuItem.withNewSession { Session session ->
            StoredProcedureQuery procedure = session.createStoredProcedureQuery('sp_update_menu_item_status')
            procedure.registerStoredProcedureParameter('applications', PostgreSQLStringArrayUserType, ParameterMode.IN)
            procedure.setParameter('applications', applicationNames as String[])
            procedure.execute()
        }
    }

    @Override
    void onApplicationEvent(HeartbeatEvent event) {
        DiscoveryClient discoveryClient = event.source as DiscoveryClient
        def applicationNames = discoveryClient.applications.registeredApplications.findAll {
            it.name.endsWith('-API')
        }.collect {
            it.name
        }.toSet()

        if (this.applicationNames != applicationNames) {
            this.applicationNames = applicationNames
            updateMenuItem()
        }
    }
}
