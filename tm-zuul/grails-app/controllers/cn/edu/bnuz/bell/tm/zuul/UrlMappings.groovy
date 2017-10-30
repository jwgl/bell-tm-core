package cn.edu.bnuz.bell.tm.zuul

class UrlMappings {

    static mappings = {
        "/"(controller: 'index')

        "/menus"(resources: 'menu', includes: ['index', 'show'])

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
