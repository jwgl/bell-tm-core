package cn.edu.bnuz.bell.profile

import cn.edu.bnuz.bell.security.SecurityService
import cn.edu.bnuz.bell.security.UserType
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class PictureController {
    SecurityService securityService

    @Value('${bell.student.picturePath}')
    String picturePath

    def show(String userId) {
        switch (securityService.userType) {
            case UserType.STUDENT:
                if (securityService.userId == userId) {
                    output(userId)
                } else {
                    render status: HttpStatus.UNAUTHORIZED
                }
                break
            case UserType.TEACHER:
                if (securityService.hasRole('ROLE_REGISTER_ADMIN')) {
                    output(userId)
                } else {
                    render status: HttpStatus.UNAUTHORIZED
                }
                break
            default:
                render status: HttpStatus.UNAUTHORIZED
                break
        }
    }

    private output(String userId) {
        File file = new File(picturePath, "${userId}.jpg")
        if (!file.exists()) {
            render status: HttpStatus.NOT_FOUND
        } else {
            render (file: file, contentType: URLConnection.guessContentTypeFromName(file.getName()))
        }
    }
}
