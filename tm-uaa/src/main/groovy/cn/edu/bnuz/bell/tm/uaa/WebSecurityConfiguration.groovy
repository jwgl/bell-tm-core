package cn.edu.bnuz.bell.tm.uaa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * UAA Web安全配置
 */
@Configuration
@Order(-20)
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage('/login')
                .permitAll()
                .and()
            .requestMatchers()
                .antMatchers('/', '/login', '/oauth/authorize', '/oauth/confirm_access')
                .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    UserDetailsService userDetailsService() {
        new TmUserDetailsService()
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        NoOpPasswordEncoder.instance
    }
}
