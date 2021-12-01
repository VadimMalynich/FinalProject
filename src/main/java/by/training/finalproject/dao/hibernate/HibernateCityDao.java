package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.City;
import by.training.finalproject.bean.Region;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HibernateCityDao {
    List<City> getAll(Session session) throws DaoException;

    /**
     * Method that find city by id and return city info
     *
     * @param id id of city
     * @return {@code City} city info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    City getCityInfo(Integer id, Session session) throws DaoException;
}
