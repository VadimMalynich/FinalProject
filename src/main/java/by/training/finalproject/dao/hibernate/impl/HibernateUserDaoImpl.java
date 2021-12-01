package by.training.finalproject.dao.hibernate.impl;

import by.training.finalproject.bean.User;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.HibernateUserDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUserDaoImpl implements HibernateUserDao {
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String AUTHORIZATION = "SELECT * FROM users WHERE email=:login";
    private static final String GET_USER_LOGIN = "SELECT email FROM users WHERE id=:id";
    private static final String GET_USER_PASSWORD = "SELECT password FROM users WHERE email=:login";
    private static final String GET_USER_ROLE = "SELECT role FROM users WHERE id=:id";


    @Override
    public List<User> getAll(Session session) throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(User.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id, Session session) throws DaoException {
        try {
            User user = session.find(User.class, id);
            session.remove(user);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Integer add(User user, Session session) throws DaoException {
        try {
            return (Integer) session.save(user);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void edit(User user, Session session) throws DaoException {
        try {
            session.update(user);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public User authorization(String login, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(AUTHORIZATION).addEntity(User.class);
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public User getUser(Integer id, Session session) throws DaoException {
        try {
            return session.find(User.class, id);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public User getUserAdInfo(Integer id, Session session) throws DaoException {
        try {
            User user = session.find(User.class, id);
            return new User(user.getId(), user.getName(), user.getPhoneNumber(), user.getCity());
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public String getUserLogin(Integer id, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_LOGIN);
            query.setParameter("id", id);
            return (String) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public String getUserPassword(String login, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_PASSWORD);
            query.setParameter("login", login);
            return (String) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Integer getUserRole(Integer id, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_ROLE);
            query.setParameter("id", id);
            return Integer.valueOf(String.valueOf(query.getSingleResult()));
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
