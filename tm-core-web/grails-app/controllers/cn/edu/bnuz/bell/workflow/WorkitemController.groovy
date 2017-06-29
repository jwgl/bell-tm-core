package cn.edu.bnuz.bell.workflow

import cn.edu.bnuz.bell.security.SecurityService
import org.springframework.security.access.annotation.Secured

/**
 * 工作项控制器
 * @author Yang Lin
 */
@Secured(['ROLE_USER'])
class WorkitemController {
    SecurityService securityService
    WorkitemService workitemService

    def index() {}

    def show(String id) {
        def userId = securityService.userId
        Workitem workItem = workitemService.get(userId, UUID.fromString(id))

        def host = request.getHeader('x-forwarded-host')
        def url = workItem.activity.url
        if (host) {
            def proto = request.getHeader('x-forwarded-proto')
            url = "${proto}://${host}" + url
        }

        redirect url: url
                .replace('${id}', workItem.instance.entityId)
                .replace('${workitem}', id)
                .replace('${userId}', userId)
                .replace('${todo}', workItem.dateProcessed ? 'done' : 'todo')
    }
}
