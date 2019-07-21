package cn.edu.bnuz.bell.tm.zuul

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

/**
 * Web安全配置
 * @author Yang Lin
 */
@EnableZuulProxy
@Configuration
@EnableOAuth2Sso
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .csrf()
                .ignoringAntMatchers('/login', '/logout')
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    }

    @Bean
    OAuth2RestOperations oAuth2RestOperations(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        new OAuth2RestTemplate(details, oauth2ClientContext)
    }
}
