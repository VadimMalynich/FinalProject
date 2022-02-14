package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.Messengers;
import by.training.finalproject.bean.User;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao extends AbstractDao<Integer, User>{
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String AUTHORIZATION = "SELECT * FROM users WHERE email=:login";
    private static final String GET_USER_LOGIN = "SELECT email FROM users WHERE id=:id";
    private static final String GET_USER_PASSWORD = "SELECT password FROM users WHERE email=:login";
    private static final String GET_USER_ROLE = "SELECT role FROM users WHERE id=:id";

    @Override
    public List<User> getAll() throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(User.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try {
            User user = session.find(User.class, id);
            session.remove(user);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Integer addUser(User user) throws DaoException {
        try {
            return (Integer) session.save(user);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public User authorization(String login) throws DaoException {
        try {
            Query query = session.createNativeQuery(AUTHORIZATION).addEntity(User.class);
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public User getUser(Integer id) throws DaoException {
        try {
            return session.find(User.class, id);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public User getUserAdInfo(Integer id) throws DaoException {
        try {
            User user = session.find(User.class, id);
            return new User(user.getId(), user.getName(), user.getPhoneNumber(), user.getCity());
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public String getUserLogin(Integer id) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_LOGIN);
            query.setParameter("id", id);
            return (String) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public String getUserPassword(String login) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_PASSWORD);
            query.setParameter("login", login);
            return (String) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Integer getUserRole(Integer id) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_ROLE);
            query.setParameter("id", id);
            return Integer.valueOf(String.valueOf(query.getSingleResult()));
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Messengers getUserMessengers(Integer id) throws DaoException {
        try{
            return session.find(Messengers.class, id);
        } catch (Exception ex){
            throw new DaoException(ex);
        }
    }

    public void addMessengers(Messengers messengers) throws DaoException {
        try{
            session.save(messengers);
        } catch (Exception ex){
            throw new DaoException(ex);
        }
    }

    public void editMessengers(Messengers messengers) throws DaoException {
        try{
            session.update(messengers);
        } catch (Exception ex){
            throw new DaoException(ex);
        }
    }
}
