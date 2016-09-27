package cn.edu.bnuz.bell.tm.uaa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Ajax退出成功处理器。
 * @author Yang Lin
 */
class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    ConsumerTokenServices consumerTokenServices

    @Override
    void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getParameter('token');
        if(token) {
            consumerTokenServices.revokeToken(token)
        }
    }
}