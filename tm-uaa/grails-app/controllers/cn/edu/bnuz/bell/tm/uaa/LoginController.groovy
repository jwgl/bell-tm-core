package cn.edu.bnuz.bell.tm.uaa

class LoginController {

    def index() {
        if (request.getHeader('X-Requested-With') == 'XMLHttpRequest') {
            return renderJson([csrf: request.getAttribute('_csrf')])
        }
    }
}
