package cn.edu.bnuz.bell.profile

class PasswordController {
    PasswordService passwordService

    def patch(String userId, String op) {
        switch (op) {
            case 'SYNC':
                passwordService.sync(userId)
                break
        }
        renderOk()
    }
}
