package cn.edu.bnuz.bell.tm.common.operation

import cn.edu.bnuz.bell.operation.TaskSchedule

class ScheduleService {
    List getTeacherSchedules(String teacherId, Integer termId) {
        TaskSchedule.executeQuery '''
select new map(
  schedule.id as id,
  task.id as taskId,
  courseClass.id as courseClassId,
  courseClass.name as courseClassName,
  courseTeacher.id as courseTeacherId,
  courseTeacher.name as courseTeacherName,
  scheduleTeacher.id as teacherId,
  scheduleTeacher.name as teacherName,
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
join courseClass.teacher courseTeacher
join schedule.teacher scheduleTeacher
left join task.courseItem courseItem
left join schedule.place place
where scheduleTeacher.id = :teacherId
  and courseClass.term.id = :termId
''', [teacherId: teacherId, termId: termId]
    }

    List getStudentSchedules(String studentId, Integer termId) {
        TaskSchedule.executeQuery '''
select new map(
  schedule.id as id,
  task.id as taskId,
  courseClass.id as courseClassId,
  courseClass.name as courseClassName,
  courseTeacher.id as courseTeacherId,
  courseTeacher.name as courseTeacherName,
  scheduleTeacher.id as teacherId,
  scheduleTeacher.name as teacherName,
  schedule.startWeek as startWeek,
  schedule.endWeek as endWeek,
  schedule.oddEven as oddEven,
  schedule.dayOfWeek as dayOfWeek,
  schedule.startSection as startSection,
  schedule.totalSection as totalSection,
  course.name as course,
  courseItem.name as courseItem,
  place.name as place,
  taskStudent.repeatType as repeatType
)
from TaskSchedule schedule
join schedule.task task
join task.courseClass courseClass
join courseClass.course course
join task.students taskStudent
join courseClass.teacher courseTeacher
join schedule.teacher scheduleTeacher
left join task.courseItem courseItem
left join schedule.place place
where taskStudent.student.id = :studentId
  and courseClass.term.id = :termId
''', [studentId: studentId, termId: termId]
    }
}
