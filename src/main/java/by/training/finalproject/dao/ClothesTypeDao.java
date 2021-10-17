package by.training.finalproject.dao;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothesTypeDao extends AbstractDao<Integer, ClothesType> {
    private static final String GET_ALL_CLOTHES_TYPE = "SELECT id, name FROM category";
    private static final String DELETE_CLOTHES_TYPE = "DELETE FROM category WHERE id=?";
    private static final String ADD_CLOTHES_TYPE = "INSERT INTO category (name) VALUES (?)";
    private static final String EDIT_CLOTHES_TYPE = "UPDATE category SET name=? WHERE id=?";
    private static final String GET_CLOTHES_TYPE_CATEGORY = "SELECT name FROM category WHERE id=?";

    @Override
    public List<ClothesType> getAll() throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<ClothesType> clothesTypes = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_CLOTHES_TYPE);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                ClothesType clothesType = new ClothesType(resSet.getInt(1), resSet.getString(2));
                clothesTypes.add(clothesType);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return clothesTypes;
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(DELETE_CLOTHES_TYPE);
            prSt.setInt(1, id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    @Override
    public void add(ClothesType type) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(ADD_CLOTHES_TYPE);
            prSt.setString(1, type.getCategory());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method edit clothes type name
     *
     * @param type object of {@code ClothesType} with new data
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void edit(ClothesType type) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(EDIT_CLOTHES_TYPE);
            prSt.setString(1, type.getCategory());
            prSt.setInt(2, type.getId());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method that find clothes category by id and return her name
     *
     * @param id id of clothes category
     * @return {@code String} name of clothes category
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public String getClothesCategory(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        String category = null;
        try {
            prSt = connection.prepareStatement(GET_CLOTHES_TYPE_CATEGORY);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                category = resSet.getString(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return category;
    }
}
