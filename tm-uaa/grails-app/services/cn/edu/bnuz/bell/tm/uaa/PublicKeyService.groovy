package cn.edu.bnuz.bell.tm.uaa

import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Transactional
class PublicKeyService {
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter

    def getKey() {
        jwtAccessTokenConverter.getKey()
    }
}
