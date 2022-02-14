package by.training.finalproject.dao.jbdc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import by.training.finalproject.dao.exception.ConnectionPoolException;

public final class ConnectionPool {
    private static final ReentrantLock LOCKER = new ReentrantLock();

    private final String driverName;
    private final String url;
    private final String user;
    private final String password;
    private final int maxSize;

    private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private static final ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {
        DBResourceManager dbResourceManager = new DBResourceManager();
        driverName = dbResourceManager.getValue("db.driver");
        url = dbResourceManager.getValue("db.url");
        user = dbResourceManager.getValue("user");
        password = dbResourceManager.getValue("password");
        maxSize = Integer.parseInt(dbResourceManager.getValue("poolSize"));
    }

    public Connection getConnection() throws ConnectionPoolException {
        LOCKER.lock();
        PooledConnection connection = null;
        try {
            while (connection == null) {
                try {
                    if (!freeConnections.isEmpty()) {
                        connection = freeConnections.take();
                        connection.getConnection().close();
                        connection = null;
                    } else if (usedConnections.size() < maxSize) {
                        connection = createConnection();
                    } else {
                        throw new ConnectionPoolException("The limit of number of database connections is exceeded");
                    }
                } catch (InterruptedException | SQLException e) {
                    Thread.currentThread().interrupt();
                    throw new ConnectionPoolException("It is impossible to connect to a database", e);
                }
            }
            usedConnections.add(connection);
        } finally {
            LOCKER.unlock();
        }
        return connection;
    }

    public void freeConnection(PooledConnection connection) throws SQLException {
        try {
            LOCKER.lock();
            connection.clearWarnings();
            connection.setAutoCommit(true);
            usedConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException(e);
        } finally {
            LOCKER.unlock();
            connection.getConnection().close();
        }
    }

    public void init() throws ConnectionPoolException {
        try {
            LOCKER.lock();
            destroy();
            Class.forName(driverName);
            for (int counter = 0; counter < maxSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException("It is impossible to initialize connection pool", e);
        } finally {
            LOCKER.unlock();
        }
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, user, password));
    }

    public void destroy() throws ConnectionPoolException {
        try {
            LOCKER.lock();
            usedConnections.addAll(freeConnections);
            freeConnections.clear();
            for (PooledConnection connection : usedConnections) {
                try {
                    connection.getConnection().close();
                } catch (SQLException e) {
                    throw new ConnectionPoolException("It's impossible to close connection", e);
                }
            }
            usedConnections.clear();
        } finally {
            LOCKER.unlock();
        }
    }
}
