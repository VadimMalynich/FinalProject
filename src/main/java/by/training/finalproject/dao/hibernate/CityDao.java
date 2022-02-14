package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.City;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CityDao extends AbstractDao<Integer, City> {
    private static final String GET_ALL = "SELECT * FROM cities";
    private static final String GET_CITY_NAME = "SELECT * FROM cities WHERE id = :id";

    @Override
    public List<City> getAll() throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(City.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try {
            City city = session.find(City.class, id);
            session.remove(city);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public City getCityInfo(Integer id) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_CITY_NAME).addEntity(City.class);
            query.setParameter("id", id);
            return (City) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
