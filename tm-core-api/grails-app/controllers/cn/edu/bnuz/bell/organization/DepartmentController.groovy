package cn.edu.bnuz.bell.organization

class DepartmentController {
    DepartmentService departmentService

    def index() {
        switch (params.q) {
            case 't':
                renderJson departmentService.getTeachingDepartments()
                break
            case 'a':
                renderJson departmentService.getAdministrativeDepartments()
                break
            case 's':
                renderJson departmentService.getStudentDepartments()
                break
            default:
                renderJson departmentService.getAllDepartments()
                break
        }
    }
}
