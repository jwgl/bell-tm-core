package cn.edu.bnuz.bell.tm.core.web

class UrlMappings {

    static mappings = {
        "/"(controller: 'index')

        "/fields"(resources: 'field', includes: ['index'])

        "/activities"(resources: 'activity', includes: ['index', 'show'])

        "/users"(resources: 'user', includes:[]) {
            "/profile"(resource: 'profile', includes: ['show', 'update'])
            "/works"(resources: 'workitem', includes: ['index', 'show'])
            "/picture"(resource: 'picture', includes: ['show'])
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
