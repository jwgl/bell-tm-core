package cn.edu.bnuz.bell.tm.common.organization

import cn.edu.bnuz.bell.organization.Student
import grails.transaction.Transactional

@Transactional
class StudentService {
    /**
     * 获取学生基本信息
     * @param studentId 学号
     * @return [id: 学号, name: 姓名, grade: 年级, subject: 专业, adminClass: 班级]
     */
    Map getStudentInfo(String studentId) {
        def results = Student.executeQuery '''
select new map (
  student.id as id,
  student.name as name,
  major.grade as grade,
  subject.name as subject,
  adminClass.name as adminClass,
  department.name as department
)
from Student student
join student.adminClass adminClass
join student.major major
join major.subject subject
join student.department department
where student.id = :studentId
''', [studentId: studentId]

        results ? results[0] as Map : null
    }

    /**
     * 获取指定学生的学院信息
     * @param studentId 学号
     * @return [id: 编号, name: 名称]
     */
    Map getDepartment(String studentId) {
        def results = Student.executeQuery '''
select new map (
  department.id as id,
  department.name as name
)
from Student student
join student.department department
where student.id = :studentId 
''', [studentId: studentId]

        results ? results[0] as Map : null
    }

    /**
     * 获取指定学生的班级信息
     * @param studentId 学号
     * @return [id: 编号, name: 名称]
     */
    Map getAdminClass(String studentId) {
        def results = Student.executeQuery '''
select new map (
  adminClass.id as id,
  adminClass.name as name
)
from Student student
join student.adminClass adminClass
where student.id = :studentId 
''', [studentId: studentId]

        results ? results[0] as Map : null
    }

    /**
     * 获取指定学生的辅导员信息
     * @param studentId 学号
     * @return [id: 教工号, name: 姓名]
     */
    Map getCounsellor(String studentId) {
        def results = Student.executeQuery '''
select new map (
  counsellor.id as id,
  counsellor.name as name
)
from Student student
join student.adminClass adminClass
join adminClass.counsellor counsellor
where student.id = :studentId 
''', [studentId: studentId]

        results ? results[0] as Map : null
    }

    /**
     * 获取指定学生的辅导员信息
     * @param studentId 学号
     * @return [id: 教工号, name: 姓名]
     */
    Map getSupervisor(String studentId) {
        def results = Student.executeQuery '''
select new map (
  supervisor.id as id,
  supervisor.name as name
)
from Student student
join student.adminClass adminClass
join adminClass.supervisor supervisor
where student.id = :studentId 
''', [studentId: studentId]

        results ? results[0] as Map : null
    }
}
