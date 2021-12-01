package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.Messengers;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface HibernateMessengersDao {
    /**
     * Method of receiving the user's messengers
     *
     * @param id id of user
     * @return object of {@code Messengers} read from database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    Messengers getUserMessengers(Integer id, Session session) throws DaoException;

    /**
     * Method of adding the user's messengers to the database
     *
     * @param messengers object of {@code Messengers} with info that will be added
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    void add(Messengers messengers, Session session) throws DaoException;

    /**
     * Method edit user messengers info
     *
     * @param messengers object of {@code Messengers} with new info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    void edit(Messengers messengers, Session session) throws DaoException;
}
