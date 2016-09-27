package cn.edu.bnuz.bell.organization

import grails.transaction.Transactional

@Transactional
class TeacherService {

    def find(String query) {
        Teacher.executeQuery '''
select new Map(
  t.id as id,
  t.name as name,
  d.name as department
)
from Teacher t
join t.department d
where t.atSchool = true
and (t.id like :query or t.name like :query or d.name like :query)
''', [query: "%${query}%"]
    }
}
