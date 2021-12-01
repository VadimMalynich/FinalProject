package by.training.finalproject.dao.hibernate.impl;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.HibernateClothesTypeDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateClothesTypeDaoImpl implements HibernateClothesTypeDao {
    private static final String GET_ALL = "SELECT * FROM category";
    private static final String GET_CLOTHES_TYPE_CATEGORY = "SELECT name FROM category WHERE id=:id";

    @Override
    public List<ClothesType> getAll(Session session) throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(ClothesType.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id, Session session) throws DaoException {
        try {
            ClothesType clothesType = session.find(ClothesType.class, id);
            session.remove(clothesType);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void add(ClothesType type, Session session) throws DaoException {
        try {
            session.save(type);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void edit(ClothesType type, Session session) throws DaoException {
        try {
            session.update(type);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public String getClothesCategory(Integer id, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_CLOTHES_TYPE_CATEGORY);
            query.setParameter("id", id);
            return (String) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
