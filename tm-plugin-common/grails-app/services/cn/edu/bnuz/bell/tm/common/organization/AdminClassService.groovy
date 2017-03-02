package cn.edu.bnuz.bell.tm.common.organization

import cn.edu.bnuz.bell.organization.AdminClass
import grails.transaction.Transactional

@Transactional
class AdminClassService {

    List<Map> findByDepartment(String departmentId, Boolean inSchool = true) {
        if (inSchool) {
            AdminClass.executeQuery '''
select new map (adminClass.id as id, adminClass.name as name)
from AdminClass adminClass
join adminClass.major major
join major.subject subject
where major.grade + subject.lengthOfSchooling > (select id / 10 from Term where active = true)
  and adminClass.department.id = :departmentId
''', [departmentId: departmentId]
        } else {
            AdminClass.executeQuery '''
select new map (adminClass.id as id, adminClass.name as name)
from AdminClass adminClass
where adminClass.department.id = :departmentId
''', [departmentId: departmentId]
        }
    }

    List<Map> findByCounsellor(String teacherId, Boolean inSchool = true) {
        if (inSchool) {
            AdminClass.executeQuery '''
select new map (adminClass.id as id, adminClass.name as name)
from AdminClass adminClass
join adminClass.major major
join major.subject subject
where major.grade + subject.lengthOfSchooling > (select id / 10 from Term where active = true)
  and adminClass.counsellor.id = :teacherId
''', [teacherId: teacherId]
        } else {
            AdminClass.executeQuery '''
select new map (adminClass.id as id, adminClass.name as name)
from AdminClass adminClass
where adminClass.counsellor.id = :teacherId
''', [teacherId: teacherId]
        }
    }

    List<Map> findBySupervisor(String teacherId, Boolean inSchool = true) {
        if (inSchool) {
            AdminClass.executeQuery '''
select new map (adminClass.id as id, adminClass.name as name)
from AdminClass adminClass
join adminClass.major major
join major.subject subject
where major.grade + subject.lengthOfSchooling > (select id / 10 from Term where active = true)
  and adminClass.supervisor.id = :teacherId
''', [teacherId: teacherId]
        } else {
            AdminClass.executeQuery '''
select new map (adminClass.id as id, adminClass.name as name)
from AdminClass adminClass
where adminClass.supervisor.id = :teacherId
''', [teacherId: teacherId]
        }
    }
}
