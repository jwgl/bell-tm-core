package cn.edu.bnuz.bell.security

import grails.gorm.hibernate.HibernateEntity
import org.codehaus.groovy.util.HashCodeHelper

/**
 * 学生角色
 * @author Yang Lin
 */
class StudentRole implements Serializable, HibernateEntity<TeacherRole> {
    private static final long serialVersionUID = 1

    String userId
    String roleId

    static mapping = {
        comment     '学生-角色'
        table       'dv_student_role'
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
        executeQuery "select roleId from StudentRole where userId = :userId", [userId: userId]
    }
}