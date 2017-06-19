package cn.edu.bnuz.bell.profile

import cn.edu.bnuz.bell.security.User
import grails.gorm.transactions.Transactional

@Transactional
class ProfileService {

    def getUserInfo(String userId) {
        User.get(userId)
    }

    def update(String userId, ProfileCommand cmd) {
        User user = User.get(userId)
        user.properties = cmd
        user.save()
    }
}
