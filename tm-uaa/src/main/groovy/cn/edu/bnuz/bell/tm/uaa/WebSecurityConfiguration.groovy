package cn.edu.bnuz.bell.tm.uaa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

/**
 * UAA Web安全配置
 */
@Configuration
@Order(-20)
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .formLogin()
                .loginPage('/login')
                .permitAll()
                .and()
            .requestMatchers()
                .antMatchers('/', '/login', '/logout', '/oauth/authorize', '/oauth/confirm_access')
                .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
            .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        new AjaxLogoutSuccessHandler()
    }

    @Bean
    UserDetailsService userDetailsService() {
        new TmUserDetailsService()
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        new NoOpPasswordEncoder()
    }
}