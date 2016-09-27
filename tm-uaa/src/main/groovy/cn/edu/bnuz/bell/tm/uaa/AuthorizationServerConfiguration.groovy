package cn.edu.bnuz.bell.tm.uaa

import cn.edu.bnuz.bell.security.OAuthClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer


/**
 * 授权服务器配置
 * @author Yang Lin
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    OAuthClientService oAuthClientService

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.authenticationManager(authenticationManager)
//    }

    @Override
    void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(oAuthClientService)
    }
}

