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
    WorkflowService workflowService

    def index() {}

    def show(String id) {
        def userId = securityService.userId
        def uuid = UUID.fromString(id)
        Workitem workItem = Workitem.get(uuid)
        if (!workItem || workItem.to.id != userId) {
            return renderNotFound()
        }

        if (!workItem.dateReceived) {
            workItem.dateReceived = new Date()
            workItem.save()
        }

        // 如果审批结束，最后工作项的操作为"同意"(ACCEPT)，活动为"查看"(*.view)
        if (!workItem.dateProcessed && workItem.action == AuditAction.ACCEPT && workItem.activity.id.endsWith('.view')) {
            workflowService.setProcessed(uuid)
        }

        log.debug(request.getServerName())
        log.debug('haha')
        redirect url: workItem.activity.url
                .replace('${id}', workItem.instance.entityId)
                .replace('${workitem}', id)
                .replace('${userId}', userId)
    }
}
