package cn.edu.bnuz.bell.tm.uaa

import cn.edu.bnuz.bell.security.BellUser
import cn.edu.bnuz.bell.security.OAuthClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerTokenServicesConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

/**
 * 授权服务器配置
 * @author Yang Lin
 */
@Configuration
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@Import(AuthorizationServerTokenServicesConfiguration.class)
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager

    @Autowired
    private OAuthClientService oAuthClientService

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter

    @Autowired
    TokenStore tokenStore

    @Override
    void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(oAuthClientService)
    }

    @Bean
    TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                DefaultOAuth2AccessToken oauth2AccessToken = accessToken as DefaultOAuth2AccessToken
                BellUser bellUser = authentication.userAuthentication.principal as BellUser
                oauth2AccessToken.setAdditionalInformation([
                        details: bellUser.details,
                        sub: bellUser.id,
                ])
                return accessToken
            }
        }
    }

    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain()
        tokenEnhancerChain.setTokenEnhancers([tokenEnhancer(), jwtAccessTokenConverter])
        endpoints.tokenStore(tokenStore)
                 .tokenEnhancer(tokenEnhancerChain)
                 .authenticationManager(authenticationManager)
    }
}
