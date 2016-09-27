package cn.edu.bnuz.bell.tm.uaa

class UrlMappings {

    static mappings = {
        get "/"(controller: 'index')
        get "/login"(controller: 'login')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
