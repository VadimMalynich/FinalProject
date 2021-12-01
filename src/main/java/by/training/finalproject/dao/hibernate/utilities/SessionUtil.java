package by.training.finalproject.dao.hibernate.utilities;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {
    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public Session openTransactionSession() {
        transaction = openSession().beginTransaction();
        return session;
    }

    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    public void commitTransactionSession() {
        transaction.commit();
        closeSession();
    }

    public void rollbackTransactionSession() {
        transaction.rollback();
        closeSession();
    }
}
