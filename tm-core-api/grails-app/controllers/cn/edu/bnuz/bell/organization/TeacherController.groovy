package cn.edu.bnuz.bell.organization

class TeacherController {
    TeacherService teacherService

    def index() {
        String query = params.q
        renderJson teacherService.find(query)
    }
}
