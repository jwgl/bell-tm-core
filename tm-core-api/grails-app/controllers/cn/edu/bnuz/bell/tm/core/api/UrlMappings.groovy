package cn.edu.bnuz.bell.tm.core.api

class UrlMappings {

    static mappings = {
        // 公共服务
        "/fields"(resources: 'field', includes: ['index'])

        "/teachers"(resources: 'teacher', includes: ['index'])

        "/departments"(resources: 'department', includes: ['index'])

        "/students"(resources: 'student', includes: []) {
            "/schedules"(resources: 'studentSchedule', includes: ['index'])
        }

        "/workflows"(resources: 'workflow', includes: []) {
            "/workitems"(action: 'workitems', includes: ['index'], method: 'GET')
        }

        "/menus"(resources: 'menu', includes: ['index'])

        // 按用户获取信息
        "/users"(resources: 'user', includes: []) {
            "/profile"(resource: 'profile', includes: ['show', 'update'])
            "/picture"(resource: 'picture', includes: ['show'])
            "/works"(resources: 'workitem', includes: ['index', 'patch']) {
                collection {
                    "/counts"(controller: 'workitem', action: 'counts', method: 'GET')
                }
            }
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
        "403"(view: '/forbidden')
        "401"(view: '/unauthorized')
    }
}
