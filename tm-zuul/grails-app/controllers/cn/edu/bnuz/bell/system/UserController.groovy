package cn.edu.bnuz.bell.system

import cn.edu.bnuz.bell.security.SecurityService

class UserController {
    SecurityService securityService

    def index() {
        renderJson([user: securityService.userDetails])
    }
}
