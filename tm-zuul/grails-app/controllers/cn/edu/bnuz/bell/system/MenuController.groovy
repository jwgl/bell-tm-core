package cn.edu.bnuz.bell.system

import cn.edu.bnuz.bell.menu.application.ApplicationMenuService

class MenuController {
    ApplicationMenuService applicationMenuService

    def index() {
        if (params.group) {
            List<String> groups = params.list('group')
            Map menus = groups.collectEntries { group ->
                [group, applicationMenuService.getUserMenus(group, request.locale)]
            }
            renderJson(menus)
        } else {
            renderOk()
        }
    }

    def show(String id) {
        renderJson applicationMenuService.getUserMenus(id, request.locale)
    }
}
