package cn.edu.bnuz.bell.tm.uaa

import cn.edu.bnuz.bell.security.UserType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User as OssUser

import cn.edu.bnuz.bell.security.User

/**
 * TM用户
 * @author Yang Lin
 */
class TmUser extends OssUser {
    String id
    String name
    String departmentId
    UserType userType

    TmUser(User user, Collection<GrantedAuthority> authorities) {
        super(user.id, user.password, user.enabled, !user.accountExpired, !user.passwordExpired,
        !user.accountLocked, authorities);
        this.id = user.id
        this.name = user.name
        this.departmentId = user.departmentId
        this.userType = userType
    }

    @Override
    public String toString() {
        "id: $id, name: $name, ${super.toString()}"
    }
}
