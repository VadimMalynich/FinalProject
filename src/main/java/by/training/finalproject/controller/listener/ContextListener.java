package by.training.finalproject.controller.listener;

import by.training.finalproject.dao.connection.ConnectionPool;
import by.training.finalproject.dao.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Error in ConnectionPool.init",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Error in ConnectionPool.destroy",e);
        }
    }
}
