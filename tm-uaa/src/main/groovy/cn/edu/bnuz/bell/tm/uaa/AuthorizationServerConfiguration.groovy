package cn.edu.bnuz.bell.tm.uaa

import cn.edu.bnuz.bell.security.OAuthClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import org.springframework.web.servlet.view.RedirectView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * 授权服务器配置
 * @author Yang Lin
 */
@Configuration
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager

    @Autowired
    private OAuthClientService oAuthClientService

    @Autowired
    private UserDetailsService userDetailsService

    @Override
    void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(oAuthClientService)
    }

    /**
     * 重定向前清除session，见https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-logout
     */
    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                 .userDetailsService(userDetailsService)
                 .addInterceptor(new HandlerInterceptorAdapter() {
            void postHandle(HttpServletRequest request,
                            HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) throws Exception {
                if (modelAndView != null && modelAndView.view instanceof RedirectView) {
                    RedirectView redirect = (RedirectView) modelAndView.view
                    String url = redirect.url
                    if (url.contains("code=") || url.contains("error=")) {
                        HttpSession session = request.getSession(false)
                        if (session != null) {
                            session.invalidate()
                        }
                    }
                }
            }
        })
    }
}

