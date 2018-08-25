package cn.edu.bnuz.bell.workflow

import cn.edu.bnuz.bell.http.ForbiddenException
import cn.edu.bnuz.bell.http.NotFoundException
import cn.edu.bnuz.bell.security.User
import grails.gorm.transactions.Transactional

@Transactional
class WorkitemService {
    WorkflowService workflowService

    List getOpenWorkitems(String userId, Long offset, Long max) {
        Workitem.executeQuery '''
select new Map (
  workflow.name as type,
  workitem.id as id,
  activity.name as activity,
  instance.title as title,
  instance.entityId as entityId,
  workitem.dateCreated as dateCreated,
  workitem.dateReceived as dateReceived,
  workitem.dateProcessed as dateProcessed,
  activity.url as url,
  fromUser.name as fromUser
)
from Workitem workitem
join workitem.from fromUser
join workitem.activity activity
join workitem.instance instance
join instance.workflow workflow
where workitem.to.id = :userId
and workitem.dateProcessed is null
order by workitem.dateCreated desc
''', [userId: userId], [offset: offset, max: max]
    }

    List getClosedWorkitems(String userId, Long offset, Long max) {
        Workitem.executeQuery '''
select new Map (
  workflow.name as type,
  workitem.id as id,
  activity.name as activity,
  instance.title as title,
  instance.entityId as entityId,
  workitem.dateCreated as dateCreated,
  workitem.dateReceived as dateReceived,
  workitem.dateProcessed as dateProcessed,
  activity.url as url,
  fromUser.name as fromUser
)
from Workitem workitem
join workitem.from fromUser
join workitem.activity activity
join workitem.instance instance
join instance.workflow workflow
where workitem.to.id = :userId
and workitem.dateProcessed is not null
order by workitem.dateProcessed desc
''', [userId: userId], [offset: offset, max: max]
    }

    /**
     * 获取未处理和已处理事项数量
     * @param userId 当前用户ID
     * @return [open: openCount, closed: closedCount]
     */
    Map getCounts(String userId) {
        def open = Workitem.countByToAndDateProcessedIsNull(User.load(userId))
        def closed = Workitem.countByToAndDateProcessedIsNotNull(User.load(userId))
        return [open: open, closed: closed]
    }

    Workitem setReceived(String userId, UUID id) {
        Workitem workItem = Workitem.get(id)
        if (!workItem) {
            throw new NotFoundException()
        }

        if (workItem.to.id != userId) {
            throw new ForbiddenException()
        }

        if (!workItem.dateReceived) {
            workflowService.setReceived(id)
        }

        // 如果审批结束，最后工作项的操作为"同意"(ACCEPT)，活动为"查看"(*.view)
        if (!workItem.dateProcessed && workItem.event == Event.ACCEPT && workItem.activitySuffix == Activities.VIEW) {
            workflowService.setProcessed(id)
        }
    }
}
