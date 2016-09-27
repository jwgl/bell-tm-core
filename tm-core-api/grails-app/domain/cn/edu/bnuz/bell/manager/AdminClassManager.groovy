package cn.edu.bnuz.bell.manager

import org.apache.commons.lang.builder.HashCodeBuilder

import cn.edu.bnuz.bell.organization.AdminClass
import cn.edu.bnuz.bell.organization.Teacher

/**
 * 行政班管理员
 * @author Yang Lin
 */
class AdminClassManager implements Serializable {
    /**
    * 行政班
    */
    AdminClass adminClass

    /**
    * 类型-1：班主任；2：辅导员
    */
    Integer type

    /**
    * 教师
    */
    Teacher teacher

    static mapping = {
        comment    '行政班管理员'
        id         composite: ['adminClass', 'type'], comment: '行政班管理员ID'
        adminClass comment: '行政班'
        type       comment: '类型-1：班主任；2：辅导员'
        teacher    comment: '教师'
    }

    boolean equals(other) {
        if (!(other instanceof AdminClassManager)) {
            return false
        }

        other.adminClass?.id == adminClass?.id && other.type == type
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if(adminClass)
            builder.append(adminClass.id)
        if(type)
            builder.append(type)
        builder.toHashCode()
    }
}
