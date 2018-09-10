package cn.edu.bnuz.bell.profile


import grails.gorm.transactions.Transactional
import org.hibernate.SessionFactory

import javax.persistence.ParameterMode
import javax.persistence.StoredProcedureQuery

@Transactional
class PasswordService {
    SessionFactory sessionFactory

    def sync(String userId) {
        def session = sessionFactory.currentSession
        StoredProcedureQuery procedure = session.createStoredProcedureCall('sp_sync_user_password')
        procedure.registerStoredProcedureParameter('p_user_id', String, ParameterMode.IN)
        procedure.setParameter('p_user_id', userId)
        procedure.execute()
    }
}
