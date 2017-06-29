package cn.edu.bnuz.bell.workflow

import grails.gorm.transactions.Transactional

@Transactional
class WorkitemService {
    WorkflowService workflowService

    Workitem get(String userId, UUID id) {
        Workitem workItem = Workitem.get(id)
        if (!workItem || workItem.to.id != userId) {
            return renderNotFound()
        }

        if (!workItem.dateReceived) {
            workflowService.setReceived(id)
        }

        // 如果审批结束，最后工作项的操作为"同意"(ACCEPT)，活动为"查看"(*.view)
        if (!workItem.dateProcessed && workItem.event == Event.ACCEPT && workItem.activitySuffix == Activities.VIEW) {
            workflowService.setProcessed(id)
        }

        return workItem
    }
}
