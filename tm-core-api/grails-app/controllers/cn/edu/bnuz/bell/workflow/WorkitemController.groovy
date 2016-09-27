package cn.edu.bnuz.bell.workflow

import cn.edu.bnuz.bell.security.SecurityService

/**
 * 工作项控制器。
 * @author Yang Lin
 */
class WorkitemController {
    SecurityService securityService
    WorkflowService workflowService

    def index() {
        Long offset = params.long("offset") ?: 0
        Long max = params.long("max") ?: 20
        switch (params.is) {
            case 'open':
                renderJson([
                    todos: workflowService.getOpenWorkitems(securityService.userId, offset, max),
                    counts: workflowService.getCounts(securityService.userId)
                ])
                break
            case 'closed':
                renderJson([
                    todos: workflowService.getClosedWorkitems(securityService.userId, offset, max),
                    counts: workflowService.getCounts(securityService.userId)
                ])
                break
        }
    }
}
