package cn.edu.bnuz.bell.tm.uaa

import cn.edu.bnuz.bell.security.OAuthClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter

/**
 * 授权服务器配置
 * @author Yang Lin
 */
@Configuration
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    OAuthClientService oAuthClientService

    @Override
    void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(oAuthClientService)
    }
}

