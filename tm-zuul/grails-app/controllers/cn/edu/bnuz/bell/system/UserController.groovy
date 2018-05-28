package cn.edu.bnuz.bell.system

import cn.edu.bnuz.bell.security.SecurityService

class UserController {
    SecurityService securityService

    def index() {
        def userDetails = securityService.userDetails
        renderJson([user: [
                id: userDetails.username,
                name: userDetails.name,
                type: userDetails.userType,
                roles: userDetails.authorities.findAll { it.startsWith('ROLE_')},
                perms: userDetails.authorities.findAll { it.startsWith('PERM_')},
        ]])
    }
}
