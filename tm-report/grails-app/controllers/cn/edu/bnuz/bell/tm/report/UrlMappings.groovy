package cn.edu.bnuz.bell.tm.report

class UrlMappings {

    static mappings = {
        "/reports"(resources: 'report')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
