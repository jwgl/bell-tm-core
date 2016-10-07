package cn.edu.bnuz.bell.menu.application

import cn.edu.bnuz.bell.security.SecurityService
import com.netflix.discovery.DiscoveryClient
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.discovery.event.HeartbeatEvent
import org.springframework.context.ApplicationListener

class ApplicationMenuService implements ApplicationListener<HeartbeatEvent>  {
    SecurityService securityService

    @Value('${bell.application.menu.expiration:6}')
    int expiration

    private ApplicationMenu applicationMenu = new ApplicationMenu()

    def getUserMenus(String groupName, Locale locale) {
        applicationMenu.getUserMenus(
                securityService.userId,
                securityService.userName,
                securityService.userPermissions,
                groupName,
                locale
        )
    }

    @Override
    public void onApplicationEvent(HeartbeatEvent event) {
        DiscoveryClient discoveryClient = event.source as DiscoveryClient
        discoveryClient.applications.registeredApplications.each {application ->
            def instances = application.instances
            if (instances.size() > 0) {
                def instance = instances[0]
                Map<String, String> metadata = instance.getMetadata()
                if (metadata.moduleMenu) {
                    JsonSlurper slurper = new JsonSlurper()
                    def moduleMenu = slurper.parseText(metadata.moduleMenu)
                    applicationMenu.merge(moduleMenu, application.name)
                }
            }
        }
        applicationMenu.evict(expiration)
    }
}
