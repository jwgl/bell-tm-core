package cn.edu.bnuz.bell.system
import cn.edu.bnuz.bell.security.SecurityService
import org.springframework.web.servlet.support.RequestContextUtils

class MenuTagLib {
    static namespace = 'b'
    private static MENU_SESSION_KEY = "user_menu"
    SecurityService securityService
    MenuService menuService

    def menu = { attrs, body ->
        def locale = RequestContextUtils.getLocale(request)

        if(securityService.principal) {
            def root = attrs.root
            def menusString = session["${MENU_SESSION_KEY}_${root}"]
            if(!menusString) {
                def roles = securityService.userRoles
                def menus = menuService.getMenus(root, roles)
                StringBuilder sb = new StringBuilder()
                menus.each {createNav it, sb, locale}
                menusString = sb.toString()
                session["${MENU_SESSION_KEY}_${root}"] = menusString
            }
            out << menusString
        }
    }

    private void createNav(def menu, StringBuilder sb, Locale locale) {
        if(menu.items) {
            sb << '<li class="nav-item dropdown"><a href="#" class="nav-link" data-toggle="dropdown" data-submenu>'
            if(menu.id == "user.profile") {
                sb << securityService.userName
            } else {
                sb << (locale.country == 'CN' ? menu.labelCn : menu.labelEn)
            }
            sb << '<span class="caret"></span></a><div class="dropdown-menu">'
            menu.items.each { create(it, sb, 1, locale) }
            sb << '</div>'
            sb << '</li>'
        } else {
            sb << '<li class="nav-item><a class="nav-link" href="'
            if(menu.url.startsWith("http://")) {
                sb << menu.url << '" target="_blank'
            } else {
                sb << createLink(uri: menu.url)
            }
            sb << '">'
            sb << (locale.country == 'CN' ? menu.labelCn : menu.labelEn)
            sb << '</a></li>'
        }
    }

    private void create(def menu, StringBuilder sb, int level, Locale locale) {
        if(menu.items) {
            sb << '<div class="dropdown dropdown-submenu"><a href="#" class="dropdown-item" data-toggle="dropdown" data-submenu >'
            sb << (locale.country == 'CN' ? menu.labelCn : menu.labelEn)
            sb << '</a>'
            sb << '<div class="dropdown-menu">'
            menu.items.each { create(it, sb, level + 1, locale) }
            sb << '</div>'
            sb << '</div>'
        } else {
            if(menu.url) {
                sb << '<a class="dropdown-item" href="'
                if(menu.url.startsWith("http://")) {
                    sb << menu.url << '" target="_blank'
                } else {
                    sb << createLink(uri: menu.url.replace('${userId}', securityService.userId))
                }
                sb << '">'
                sb << (locale.country == 'CN' ? menu.labelCn : menu.labelEn)
                sb << '</a>'
            }
        }
    }
}