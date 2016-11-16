package cn.edu.bnuz.bell.tm.common.organization

import cn.edu.bnuz.bell.organization.Department

class DepartmentService {

    /**
     * 所有部门
     * @return [id:id, name:name]*
     */
    def getAllDepartments() {
        Department.executeQuery 'SELECT new map(id as id, name as name) FROM Department where enabled = true'
    }

    /**
     * 获取行政部门
     * @return [id:id, name:name]*
     */
    def getAdministrativeDepartments() {
        Department.executeQuery 'SELECT new map(id as id, name as name) FROM Department where isTeaching = false and enabled = true'
    }

    /**
     * 获取教学单位
     * @return [id:id, name:name]*
     */
    def getTeachingDepartments() {
        Department.executeQuery 'SELECT new map(id as id, name as name) FROM Department where isTeaching = true and enabled = true'
    }
}
