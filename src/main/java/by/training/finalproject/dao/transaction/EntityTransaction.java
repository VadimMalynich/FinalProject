package by.training.finalproject.dao.transaction;

import by.training.finalproject.dao.AbstractDao;
import by.training.finalproject.dao.connection.ConnectionPool;
import by.training.finalproject.dao.connection.PooledConnection;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction implements Transaction {
    private Connection connection;

    @Override
    public void initTransaction(AbstractDao... daos) throws ConnectionPoolException, DaoException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    @Override
    public void endTransaction() throws DaoException {
        if (connection == null) {
            throw new DaoException("Transaction cannot be completed: connection is lost");
        }
        try {
            ConnectionPool.getInstance().freeConnection((PooledConnection) connection);
            connection = null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commit() throws DaoException {
        if (connection == null) {
            throw new DaoException("Transaction cannot be committed: connection is lost");
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        if (connection == null) {
            throw new DaoException("Transaction cannot be rollback: connection is lost");
        }
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

