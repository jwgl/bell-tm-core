package cn.edu.bnuz.bell.tm.uaa

import grails.converters.JSON
import org.springframework.security.core.context.SecurityContextHolder

class UserController {
    def index() {
        render SecurityContextHolder.context?.authentication?.principal as JSON
    }
}
