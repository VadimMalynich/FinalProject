package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.Comments;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.util.List;

public interface HibernateCommentDao {

    void delete(Integer id, Session session) throws DaoException;

    void add(Comments comment, Session session) throws DaoException;

    /**
     * Method gets all needed data from database and put into entity class. Then collect entity classes into {@code List}
     *
     * @param adId id of ad for which will be got comments
     * @return {@code List} of objects that stored data from database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     List<Comments> getAll(Integer adId, Session session) throws DaoException;
    /**
     * Method for searching for comments with specified ad id and returning their number
     *
     * @param adId id of ad
     * @return count of record with specified ad id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     Integer getCommentCount(Integer adId, Session session) throws DaoException;
}
