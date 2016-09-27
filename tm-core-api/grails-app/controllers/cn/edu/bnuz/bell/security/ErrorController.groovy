package cn.edu.bnuz.bell.security

import org.springframework.http.HttpStatus

/**
 * 错误控制器
 * @author Yang Lin
 */
class ErrorController {
    def index() {
        render status: HttpStatus.BAD_REQUEST
    }
}
