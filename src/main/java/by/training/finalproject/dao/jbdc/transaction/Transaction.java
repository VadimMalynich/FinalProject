package by.training.finalproject.dao.jbdc.transaction;

import by.training.finalproject.dao.jbdc.AbstractDao;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

public interface Transaction {
    /**
     * Method setting connection from {@link by.training.finalproject.dao.jbdc.connection.ConnectionPool} into {@code daos}
     *
     * @param daos {@code AbstractDao} classes in which will be setting connection
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     * @throws DaoException            if an error occurs when reading data from the database
     */
    void initTransaction(AbstractDao... daos) throws ConnectionPoolException, DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;

    void endTransaction() throws DaoException;
}
