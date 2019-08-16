package cn.edu.bnuz.bell.system

import cn.edu.bnuz.bell.security.SecurityService

class UserController {
    SecurityService securityService

    def index() {
        def userDetails = securityService.userDetails as Map
        renderJson([
                user: [
                        id          : userDetails.id,
                        name        : userDetails.name,
                        type        : userDetails.userType,
                        departmentId: userDetails.departmentId,
                        phoneNumber : userDetails.longPhone,
                ]
        ])
    }
}
