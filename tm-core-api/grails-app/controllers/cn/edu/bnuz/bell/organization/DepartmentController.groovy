package cn.edu.bnuz.bell.organization

class DepartmentController {
    DepartmentService departmentService

    def index() {
        if (params.q == 't') {
            renderJson departmentService.getTeachingDepartments()
        } else if (params.q == 'a') {
            renderJson departmentService.getAdministrativeDepartments()
        } else {
            renderJson departmentService.getAllDepartments()
        }
    }
}
