package cn.edu.bnuz.bell.tm.common.operation

import cn.edu.bnuz.bell.master.Term
import cn.edu.bnuz.bell.operation.TaskSchedule

class ScheduleService {
    List getTeacherSchedules(String teacherId, Term term) {
        TaskSchedule.executeQuery '''
select new map(
  schedule.id as id,
  schedule.startWeek as startWeek,
  schedule.endWeek as endWeek,
  schedule.oddEven as oddEven,
  schedule.dayOfWeek as dayOfWeek,
  schedule.startSection as startSection,
  schedule.totalSection as totalSection,
  course.name as course,
  courseItem.name as courseItem,
  place.name as place
)
from TaskSchedule schedule
join schedule.task task
join task.courseClass courseClass
join courseClass.course course
left join task.courseItem courseItem
left join schedule.place place
where schedule.teacher.id = :teacherId
  and courseClass.term = :term
''', [teacherId: teacherId, term: term]
    }

    List getStudentSchedules(String studentId, Term term) {
        TaskSchedule.executeQuery '''
select new map(
  schedule.id as id,
  schedule.startWeek as startWeek,
  schedule.endWeek as endWeek,
  schedule.oddEven as oddEven,
  schedule.dayOfWeek as dayOfWeek,
  schedule.startSection as startSection,
  schedule.totalSection as totalSection,
  course.name as course,
  courseItem.name as courseItem,
  place.name as place
)
from TaskSchedule schedule
join schedule.task task
join task.courseClass courseClass
join courseClass.course course
join task.students taskStudent
left join task.courseItem courseItem
left join schedule.place place
where taskStudent.student.id = :studentId
  and courseClass.term = :term
''', [studentId: studentId, term: term]
    }
}
