package by.training.finalproject.controller.listener;

import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.hibernate.utilities.HibernateUtil;
import by.training.finalproject.dao.jbdc.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final Logger userLogger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            userLogger.fatal("Error in ConnectionPool.init", e);
        }
//        HibernateUtil.open();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
            userLogger.error("Error in ConnectionPool.destroy", e);
        }
//        HibernateUtil.close();
    }
}
