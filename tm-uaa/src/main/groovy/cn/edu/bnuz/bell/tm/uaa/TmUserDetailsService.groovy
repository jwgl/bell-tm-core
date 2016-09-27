package cn.edu.bnuz.bell.tm.uaa

import cn.edu.bnuz.bell.security.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * TM UserDetail服务
 * @author Yang Lin
 */
class TmUserDetailsService implements UserDetailsService {
    protected Logger log = LoggerFactory.getLogger(getClass())

    @Override
    public UserDetails loadUserByUsername(String username) {
        User.withNewSession {
            User.withTransaction {
                User user = User.get(username)
                if (!user) {
                    throw new NoStackUsernameNotFoundException()
                }
                Collection<GrantedAuthority> authorities = []
                authorities << new SimpleGrantedAuthority(Roles.USER)

                switch (user.userType) {
                    case UserType.TEACHER:
                        loadTeacherAuthorities(authorities, user)
                        break
                    case UserType.STUDENT:
                        loadStudentAuthorities(authorities, user)
                        break
                    case UserType.EXTERNAL:
                        loadExternalAuthorities(authorities, user)
                }

                // 角色权限
                def roles = authorities.collect { it.authority }
                def permissions = RolePermission.findPermissionsByRoles roles
                authorities.addAll permissions.collect { new SimpleGrantedAuthority("ROLE_${it.id}") }

                log.debug authorities.toString()
                return new TmUser(user, authorities)
            }
        }
    }

    private Collection<GrantedAuthority> loadTeacherAuthorities(Collection<GrantedAuthority> authorities, User user) {
        // 固定角色
        authorities << new SimpleGrantedAuthority(Roles.TEACHER)

        // 配置角色
        authorities.addAll user.roles.collect { new SimpleGrantedAuthority(it.role.id) }

        // 模块角色
        authorities.addAll TeacherRole.getRoles(user.id).collect {new SimpleGrantedAuthority(it)}

        return authorities
    }

    private Collection<GrantedAuthority> loadStudentAuthorities(Collection<GrantedAuthority> authorities, User user) {
        // 固定角色
        authorities << new SimpleGrantedAuthority(Roles.STUDENT)

        // 模块角色
        authorities.addAll StudentRole.getRoles(user.id).collect {new SimpleGrantedAuthority(it)}

        return authorities
    }

    private Collection<GrantedAuthority> loadExternalAuthorities(Collection<GrantedAuthority> authorities, User user) {
        // 固定角色
        authorities << new SimpleGrantedAuthority(Roles.EXTERNAL)

        // 模块角色
        authorities.addAll ExternalRole.getRoles(user.id).collect {new SimpleGrantedAuthority(it)}

        return authorities
    }
}