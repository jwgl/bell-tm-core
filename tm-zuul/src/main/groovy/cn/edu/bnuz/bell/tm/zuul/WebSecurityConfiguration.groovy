package cn.edu.bnuz.bell.tm.zuul

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

/**
 * Web安全配置
 * @author Yang Lin
 */

@Configuration
@EnableOAuth2Sso
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .csrf()
                .ignoringAntMatchers('/login', '/logout')
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    }
}

