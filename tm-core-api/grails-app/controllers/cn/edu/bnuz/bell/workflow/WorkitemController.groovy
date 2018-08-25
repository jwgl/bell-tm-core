package cn.edu.bnuz.bell.workflow

import cn.edu.bnuz.bell.http.ServiceExceptionHandler

/**
 * 工作项控制器。
 * @author Yang Lin
 */
class WorkitemController implements ServiceExceptionHandler {
    WorkitemService workitemService

    def index(String userId) {
        Long offset = params.long("offset") ?: 0
        Long max = params.long("max") ?: 10
        List list
        switch (params.is) {
            case 'open':
                list = workitemService.getOpenWorkitems(userId, offset, max)
                break
            case 'closed':
                list = workitemService.getClosedWorkitems(userId, offset, max)
                break
            default:
                list = []
        }
        renderCount list.size()
        renderJson list
    }

    def patch(String userId, String id, String op) {
        switch (op) {
            case 'RECEIVED':
                workitemService.setReceived(userId, UUID.fromString(id))
                break
        }
        renderOk()
    }

    def counts(String userId) {
        renderJson workitemService.getCounts(userId)
    }
}
