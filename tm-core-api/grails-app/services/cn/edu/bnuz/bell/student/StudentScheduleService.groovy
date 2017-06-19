package cn.edu.bnuz.bell.student

import cn.edu.bnuz.bell.operation.TaskSchedule
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class StudentScheduleService {

    def getSchedules(Integer termId, String studentId) {
        TaskSchedule.executeQuery '''
select new map (
  taskSchedule.id as id,
  task.id as taskId,
  courseClass.id as courseClassId,
  courseClass.name as courseClassName,
  courseTeacher.id as courseTeacherId,
  courseTeacher.name as courseTeacherName,
  teacher.id as teacherId,
  teacher.name as teacherName,
  taskSchedule.startWeek as startWeek,
  taskSchedule.endWeek as endWeek,
  taskSchedule.oddEven as oddEven,
  taskSchedule.dayOfWeek as dayOfWeek,
  taskSchedule.startSection as startSection,
  taskSchedule.totalSection as totalSection,
  course.name as course,
  courseItem.name as courseItem,
  place.name as place
)
from CourseClass courseClass
join courseClass.tasks task
join task.schedules taskSchedule
join task.students taskStudent
join courseClass.course course
join courseClass.teacher courseTeacher
join taskSchedule.teacher teacher
join taskSchedule.place place
left join task.courseItem courseItem
where courseClass.term.id = :termId
and taskStudent.student.id = :studentId
''', [termId: termId, studentId: studentId]
    }
}
