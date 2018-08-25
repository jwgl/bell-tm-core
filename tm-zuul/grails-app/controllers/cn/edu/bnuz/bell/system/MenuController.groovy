package cn.edu.bnuz.bell.system

import org.springframework.http.HttpStatus

class MenuController {
    MenuService menuService

    def index() {
        def menu = menuService.getUserMenus()
        if (menu) {
            render text: menu
        } else {
            render status: HttpStatus.OK
        }
    }
}
