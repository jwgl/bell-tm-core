package cn.edu.bnuz.bell.system

import cn.edu.bnuz.bell.security.SecurityService
import org.springframework.web.servlet.support.RequestContextUtils

class MenuController {
    SecurityService securityService
    MenuService menuService
    def index() {
        def userId = securityService.userId
        def userName = securityService.userName
        def roles = securityService.userRoles
        def chinese = RequestContextUtils.getLocale(request).country == 'CN'
        def main = menuService.getUserMenus('main', userId, userName, roles, chinese)
        def user = menuService.getUserMenus('user', userId, userName, roles, chinese)
        renderJson([main: main, user: user])
    }
}
