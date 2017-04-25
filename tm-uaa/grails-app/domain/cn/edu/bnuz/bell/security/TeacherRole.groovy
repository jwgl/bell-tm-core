package cn.edu.bnuz.bell.security

import org.codehaus.groovy.util.HashCodeHelper

/**
 * 教师角色
 * @author Yang Lin
 */
class TeacherRole implements Serializable {
    private static final long serialVersionUID = 1

    String userId
    String roleId

    static mapping = {
        comment     '教师-角色'
        table       'dv_teacher_role'
        id          composite: ['roleId', 'userId'], comment: 'ID'
        userId      comment: '用户'
        roleId      comment: '角色'
    }

    boolean equals(other) {
        if (!(other instanceof UserRole)) {
            return false
        }

        other.userId == userId && other.roleId == roleId
    }

    int hashCode() {
        int hash = HashCodeHelper.initHash()
        hash = HashCodeHelper.updateHash(hash, userId)
        hash = HashCodeHelper.updateHash(hash, roleId)
        hash
    }

    static List<String> getRoles(String userId) {
        TeacherRole.executeQuery "select roleId from TeacherRole where userId=:userId", [userId: userId]
    }
}
