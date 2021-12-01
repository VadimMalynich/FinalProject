package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.User;
import by.training.finalproject.bean.UserRole;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface HibernateUserDao {
    List<User> getAll(Session session) throws DaoException;

    void delete(Integer id, Session session) throws DaoException;

    Integer add(User user, Session session) throws DaoException;

    /**
     * Method edit info about user in database
     *
     * @param user object of {@code User} with new user info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    void edit(User user, Session session) throws DaoException;

    /**
     * Method for getting user info
     *
     * @param login user login
     * @return object of {@code User} with filled data
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    User authorization(String login, Session session) throws DaoException;

    /**
     * Method for getting user info
     *
     * @param id user id
     * @return object of {@code User} with needed info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    User getUser(Integer id, Session session) throws DaoException;

    /**
     * Method for getting user info that needed for displaying in ad
     *
     * @param id id of ad
     * @return object of {@code User} with the necessary data filled in
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    User getUserAdInfo(Integer id, Session session) throws DaoException;

    /**
     * Method for getting user login by user id
     *
     * @param id user id
     * @return user login
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    String getUserLogin(Integer id, Session session) throws DaoException;

    /**
     * Method for getting user login
     *
     * @param login user login
     * @return user password
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    String getUserPassword(String login, Session session) throws DaoException;

    /**
     * Method for getting user role
     *
     * @param id id of user
     * @return {@code Integer} value of role
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    Integer getUserRole(Integer id, Session session) throws DaoException;
}
