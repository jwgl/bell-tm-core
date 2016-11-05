package cn.edu.bnuz.bell.organization

import cn.edu.bnuz.bell.tm.common.organization.TeacherService

class TeacherController {
    TeacherService teacherService

    def index() {
        String query = params.q
        renderJson teacherService.find(query)
    }
}
