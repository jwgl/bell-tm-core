package cn.edu.bnuz.bell.student

import cn.edu.bnuz.bell.master.TermService
import cn.edu.bnuz.bell.operation.ScheduleService

class StudentScheduleController {
    ScheduleService scheduleService
    TermService termService

    def index(String studentId) {
        def term = termService.activeTerm
        renderJson([
                term     : [
                        id         : term.id,
                        startWeek  : term.startWeek,
                        endWeek    : term.endWeek,
                        currentWeek: term.currentWeek,
                ],
                schedules: scheduleService.getStudentSchedules(term.id, studentId),
        ])
    }
}
