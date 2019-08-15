package cn.edu.bnuz.bell.tm.uaa

class UrlMappings {

    static mappings = {
        get "/"(controller: 'index')
        get "/login"(controller: 'login')
        get "/publicKey"(controller: 'publicKey')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
