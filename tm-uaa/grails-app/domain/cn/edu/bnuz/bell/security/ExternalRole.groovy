package cn.edu.bnuz.bell.security

import grails.gorm.hibernate.HibernateEntity

/**
 * 学生角色
 * @author Yang Lin
 */
class ExternalRole implements Serializable, HibernateEntity<TeacherRole> {
    private static final long serialVersionUID = 1

    String userId
    String roleId

    static mapping = {
        comment     '外部人员-角色'
        table       'dv_external_role'
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
        Objects.hash(userId, roleId)
    }

    static List<String> getRoles(String userId) {
        executeQuery "select roleId from ExternalRole where userId = :userId", [userId: userId]
    }
}