package cn.edu.bnuz.bell.tm.uaa

import org.springframework.http.HttpStatus

class LoginController {

    def index() {
        if (request.getHeader('X-Requested-With') == 'XMLHttpRequest') {
            if (params.containsKey('error')) {
                return render(status: HttpStatus.UNAUTHORIZED)
            } else {
                return renderJson([csrf: request.getAttribute('_csrf')])
            }
        }
        return render(status: HttpStatus.OK)
    }
}
