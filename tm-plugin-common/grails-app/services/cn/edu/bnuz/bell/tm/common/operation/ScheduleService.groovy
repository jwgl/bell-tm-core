package cn.edu.bnuz.bell.tm.common.operation

import cn.edu.bnuz.bell.master.Term
import cn.edu.bnuz.bell.operation.TaskSchedule

class ScheduleService {
    List getTeacherSchedules(String teacherId, Term term) {
        TaskSchedule.executeQuery '''
select new Map(
  schedule.id as scheduleId,
  schedule.startWeek as startWeek,
  schedule.endWeek as endWeek,
  schedule.oddEven as oddEven,
  schedule.dayOfWeek as dayOfWeek,
  schedule.startSection as startSection,
  schedule.totalSection as totalSection,
  courseItem.name as courseItem,
  place.name as place,
  course.name as course
)
from TaskSchedule schedule
join schedule.task task
join task.courseClass courseClass
join courseClass.course course
join schedule.teacher teacher
left join schedule.place place
left join task.courseItem courseItem
where teacher.id = :teacherId
  and courseClass.term = :term
''', [teacherId: teacherId, term: term]
    }
}
