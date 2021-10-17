package by.training.finalproject.dao.transaction;

import by.training.finalproject.dao.AbstractDao;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

public interface Transaction {
    /**
     * Method setting connection from {@link by.training.finalproject.dao.connection.ConnectionPool} into {@code daos}
     *
     * @param daos {@code AbstractDao} classes in which will be setting connection
     * @throws ConnectionPoolException
     * @throws DaoException
     */
    void initTransaction(AbstractDao... daos) throws ConnectionPoolException, DaoException;
    void commit() throws DaoException;
    void rollback() throws DaoException;
    void endTransaction() throws DaoException;
}
