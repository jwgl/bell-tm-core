package cn.edu.bnuz.bell.master

import grails.plugin.cache.Cacheable

/**
 * 学期服务
 * @author Yang Lin
 */
class TermService {
    @Cacheable("term.activeTerm")
    def getActiveTerm() {
        Term.findByActive(true)
    }

    @Cacheable("term.minInSchoolGrade")
    def getMinInSchoolGrade() {
        getActiveTerm().id.intdiv(10) - 3
    }
}
