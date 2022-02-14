package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.Entity;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.util.List;

/**
 * The top of the DAO pattern hierarchy. Contains common methods for descendant classes
 *
 * @param <K> data type for initializing the primary key
 * @param <T> entity class that will be stored data from database, entity class should extend from {@link Entity}
 */
public abstract class AbstractDao<K, T extends Entity> {
    /**
     * Object for manipulate data between server and database
     */
    protected Session session;

    /**
     * Method gets all needed data from database and put into entity class. Then collect entity classes into {@code List}
     *
     * @return {@code List} of objects that stored data from database
     * @throws DaoException if an error occurs when reading data from the database
     */
    public abstract List<T> getAll() throws DaoException;

    /**
     * Method add object {@code t} into database
     *
     * @param t an object of the class with information to add to the database
     * @throws DaoException if an error occurs when reading data from the database
     */
    public void add(T t) throws DaoException {
        try {
            session.save(t);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    /**
     * Method that deletes all records from the database by ID
     *
     * @param id the unique identifier of the record in the database, which is the primary key
     * @throws DaoException if an error occurs when reading data from the database
     */
    public abstract void delete(K id) throws DaoException;

    /**
     * Method edit clothes type name
     *
     * @param t object with new data
     * @throws DaoException if an error occurs when reading data from the database
     */
    public void edit(T t) throws DaoException {
        try {
            session.update(t);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}