package cn.edu.bnuz.bell.profile

import cn.edu.bnuz.bell.security.SecurityService
import cn.edu.bnuz.bell.security.UserType
import grails.util.Holders
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class PictureController {
    SecurityService securityService

    @Value('${bell.student.picturePath}')
    String picturePath

    def show(String userId) {
        if (securityService.userType == UserType.STUDENT) {
            if (securityService.userId != userId) {
                render status: HttpStatus.UNAUTHORIZED
            } else {
                output(userId)
            }
        } else {
            output(userId)
        }
    }

    private output(String userId) {
        File file = new File(picturePath, "${userId}.jpg")
        if (!file.exists()) {
            render status: HttpStatus.NOT_FOUND
        } else {
            response.contentType = URLConnection.guessContentTypeFromName(file.getName())
            response.outputStream << file.bytes
            response.outputStream.flush()
        }
    }
}
