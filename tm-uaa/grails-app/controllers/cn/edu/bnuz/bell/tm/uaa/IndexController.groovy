package cn.edu.bnuz.bell.tm.uaa

class IndexController {

    def index() {
        if (request.getHeader('X-Requested-With') == 'XMLHttpRequest') {
            response.sendRedirect('/login')
        } else {
            response.sendRedirect('/')
        }
    }
}
