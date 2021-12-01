package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.util.List;

public interface HibernateClothesTypeDao {
    List<ClothesType> getAll(Session session) throws DaoException;

    void delete(Integer id, Session session) throws DaoException;

    void add(ClothesType type, Session session) throws DaoException;

    /**
     * Method edit clothes type name
     *
     * @param type object of {@code ClothesType} with new data
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    void edit(ClothesType type, Session session) throws DaoException;

    /**
     * Method that find clothes category by id and return her name
     *
     * @param id id of clothes category
     * @return {@code String} name of clothes category
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    String getClothesCategory(Integer id, Session session) throws DaoException;
}
