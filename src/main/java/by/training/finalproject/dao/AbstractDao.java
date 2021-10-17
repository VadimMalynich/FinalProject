package by.training.finalproject.dao;

import by.training.finalproject.bean.Entity;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * The top of the DAO pattern hierarchy. Contains common methods for descendant classes
 *
 * @param <K> data type for initializing the primary key
 * @param <T> entity class that will be stored data from database, entity class should extend from {@link Entity}
 */
public abstract class AbstractDao<K, T extends Entity> {
    /**
     * Object that will be establishing a connection between server and database
     */
    protected Connection connection;

    /**
     * Method gets all needed data from database and put into entity class. Then collect entity classes into {@code List}
     *
     * @return {@code List} of objects that stored data from database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public abstract List<T> getAll() throws DaoException, ConnectionPoolException;

    /**
     * Method add object {@code t} into database
     *
     * @param t an object of the class with information to add to the database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public abstract void add(T t) throws DaoException, ConnectionPoolException;

    /**
     * Method that deletes all records from the database by ID
     *
     * @param id the unique identifier of the record in the database, which is the primary key
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public abstract void delete(K id) throws DaoException, ConnectionPoolException;

    /**
     * Method that close statement after execution of sql scripts
     *
     * @param statement object of class that implement interface {@code Statement}
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void close(Statement statement) throws ConnectionPoolException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Can not close statement", e);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
