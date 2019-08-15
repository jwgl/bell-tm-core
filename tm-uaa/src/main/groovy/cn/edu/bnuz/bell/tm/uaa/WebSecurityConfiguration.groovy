package cn.edu.bnuz.bell.tm.uaa

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.firewall.HttpFirewall
import org.springframework.security.web.firewall.StrictHttpFirewall

/**
 * UAA Web安全配置
 */
@Configuration
@Order(-20)
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage('/login')
                .permitAll()
                .and()
            .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and()
            .requestMatchers()
                .antMatchers('/', '/login', '/publicKey', '/error', '/oauth/authorize', '/oauth/confirm_access')
                .and()
            .authorizeRequests()
                .antMatchers('/publicKey', '/error').permitAll()
                .anyRequest().authenticated()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder())
    }

    @Override
    void configure(WebSecurity web) throws Exception {
         web.httpFirewall(allowUrlEncodedSlashHttpFirewall())
    }

    @Bean
    UserDetailsService userDetailsService() {
        new TmUserDetailsService()
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        NoOpPasswordEncoder.instance
    }

    @Bean
    CsrfTokenRepository csrfTokenRepository() {
        def repo = CookieCsrfTokenRepository.withHttpOnlyFalse()
        repo.setCookieName('UAA-XSRF-TOKEN')
        repo.setHeaderName('X-UAA-XSRF-TOKEN')
        return repo
    }

    @Bean
    HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall()
        firewall.setAllowUrlEncodedSlash(true)
        firewall.setAllowSemicolon(true)
        return firewall
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean()
    }
}
