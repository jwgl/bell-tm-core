package cn.edu.bnuz.bell.security

import org.apache.commons.lang.builder.HashCodeBuilder

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
        def builder = new HashCodeBuilder()
        builder.append(userId)
        builder.append(roleId)
        builder.toHashCode()
    }

    static List<String> getRoles(String userId) {
        TeacherRole.executeQuery "select roleId from TeacherRole where userId=:userId", [userId: userId]
    }
}
