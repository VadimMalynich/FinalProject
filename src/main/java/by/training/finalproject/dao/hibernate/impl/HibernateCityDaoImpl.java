package by.training.finalproject.dao.hibernate.impl;

import by.training.finalproject.bean.City;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.HibernateCityDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateCityDaoImpl implements HibernateCityDao {
    private static final String GET_ALL = "SELECT * FROM cities";
    private static final String GET_CITY_NAME = "SELECT * FROM cities WHERE id = :id";

    @Override
    public List<City> getAll(Session session) throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(City.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public City getCityInfo(Integer id, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_CITY_NAME).addEntity(City.class);
            query.setParameter("id", id);
            return (City) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
