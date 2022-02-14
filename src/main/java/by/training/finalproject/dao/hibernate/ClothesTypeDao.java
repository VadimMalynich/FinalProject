package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ClothesTypeDao extends AbstractDao<Integer, ClothesType> {
    private static final String GET_ALL = "SELECT * FROM category";
    private static final String GET_CLOTHES_TYPE_CATEGORY = "SELECT name FROM category WHERE id=:id";

    @Override
    public List<ClothesType> getAll() throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(ClothesType.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try {
            ClothesType clothesType = session.find(ClothesType.class, id);
            session.remove(clothesType);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public String getClothesCategory(Integer id) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_CLOTHES_TYPE_CATEGORY);
            query.setParameter("id", id);
            return (String) query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
