package cn.edu.bnuz.bell.student

import cn.edu.bnuz.bell.master.TermService

class StudentScheduleController {
    StudentScheduleService studentScheduleService
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
                schedules: studentScheduleService.getSchedules(term.id, studentId),
        ])
    }
}
