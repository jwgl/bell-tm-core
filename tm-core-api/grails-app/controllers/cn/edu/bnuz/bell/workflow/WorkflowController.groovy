package cn.edu.bnuz.bell.workflow

/**
 * 工作流控制器
 * @author Yang Lin
 */
class WorkflowController {
    WorkflowService workflowService
    def workitems(String workflowId) {
        renderJson workflowService.getInstanceWorkitems(UUID.fromString(workflowId))
    }
}
