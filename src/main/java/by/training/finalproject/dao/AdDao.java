package by.training.finalproject.dao;

import by.training.finalproject.bean.*;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdDao extends AbstractDao<Integer, AdInfo> {
    private static final String GET_ALL_ADS_INFO = "SELECT id, user_id, category_id, date, topic, description, picture FROM ads_info";
    private static final String DELETE_AD = "DELETE FROM ads_info WHERE id=?";
    private static final String ADD_AD = "INSERT INTO ads_info (user_id, category_id, date, topic, material, size, sex, description, picture) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String EDIT_AD = "UPDATE ads_info SET category_id=?, date=?, topic=?, material=?, size=?, sex=?, description=?, picture=? WHERE id=?";
    private static final String GET_AD_INFO = "SELECT user_id, category_id, date, topic, material, size, sex, description, picture FROM ads_info WHERE id=?";
    private static final String GET_USER_ADS = "SELECT id, user_id, category_id, date, topic, description, picture FROM ads_info WHERE user_id=?";
    private static final String GET_CLOTHES_TYPE_COUNT = "SELECT * FROM ads_info WHERE category_id=?";
    private static final String DELETE_LIKE = "DELETE FROM likes WHERE ad_info_id=? AND user_id=?";
    private static final String ADD_LIKE = "INSERT INTO likes (ad_info_id, user_id) VALUES (?,?)";
    private static final String IS_LIKE = "SELECT * FROM likes WHERE ad_info_id=? AND user_id=?";
    private static final String GET_LIKES_COUNT = "SELECT * FROM likes WHERE ad_info_id=?";

    @Override
    public List<AdInfo> getAll() throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<AdInfo> ads = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_ADS_INFO);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                Ad ad = new Ad(resSet.getDate(4), resSet.getString(5), resSet.getString(6),
                        resSet.getString(7));
                User userInfo = new User(resSet.getInt(2));
                ClothesType type = new ClothesType(resSet.getInt(3));
                AdInfo adInfo = new AdInfo(resSet.getInt(1), userInfo, type, ad);
                ads.add(adInfo);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return ads;
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(DELETE_AD);
            prSt.setInt(1, id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    @Override
    public void add(AdInfo adInfo) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(ADD_AD);
            prSt.setInt(1, adInfo.getUserInfo().getId());
            prSt.setInt(2, adInfo.getCategoryInfo().getId());
            prSt.setDate(3, adInfo.getAd().getDate());
            prSt.setString(4, adInfo.getAd().getTopic());
            prSt.setString(5, adInfo.getAd().getMaterial());
            prSt.setInt(6, adInfo.getAd().getSize().getValue());
            prSt.setInt(7, adInfo.getAd().getSex().getValue());
            prSt.setString(8, adInfo.getAd().getDescription());
            prSt.setString(9, adInfo.getAd().getPicture());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method edit data about ad in database
     *
     * @param adInfo object with new ad info that replace data in database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void edit(AdInfo adInfo) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(EDIT_AD);
            prSt.setInt(1, adInfo.getCategoryInfo().getId());
            prSt.setDate(2, adInfo.getAd().getDate());
            prSt.setString(3, adInfo.getAd().getTopic());
            prSt.setString(4, adInfo.getAd().getMaterial());
            prSt.setInt(5, adInfo.getAd().getSize().getValue());
            prSt.setInt(6, adInfo.getAd().getSex().getValue());
            prSt.setString(7, adInfo.getAd().getDescription());
            prSt.setString(8, adInfo.getAd().getPicture());
            prSt.setInt(9, adInfo.getId());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method get ad info from database
     *
     * @param id of record which need to read in database
     * @return object of {@code AdInfo} filled with data
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public AdInfo getAdInfo(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        AdInfo adInfo = null;
        try {
            prSt = connection.prepareStatement(GET_AD_INFO);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                Ad ad = new Ad(resSet.getDate(3), resSet.getString(4), resSet.getString(5),
                        ClothesSize.getByCode(resSet.getInt(6)), ClothesSex.getByCode(resSet.getInt(7)),
                        resSet.getString(8), resSet.getString(9));
                User user = new User(resSet.getInt(1));
                ClothesType type = new ClothesType(resSet.getInt(2));
                adInfo = new AdInfo(id, user, type, ad);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return adInfo;
    }

    /**
     * Method that read and return all ads that user put
     *
     * @param id of user whose ads we need to read
     * @return {@code List} of user ad objects
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public List<AdInfo> getUserAds(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<AdInfo> ads = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_USER_ADS);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                Ad ad = new Ad();
                ad.setDate(resSet.getDate(4));
                ad.setTopic(resSet.getString(5));
                ad.setDescription(resSet.getString(6));
                ad.setPicture(resSet.getString(7));
                User user = new User(resSet.getInt(2));
                ClothesType type = new ClothesType(resSet.getInt(3));
                AdInfo adInfo = new AdInfo(resSet.getInt(1), user, type, ad);
                ads.add(adInfo);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return ads;
    }

    /**
     * Method for searching for ads with specified id and returning their number
     *
     * @param id of the type of clothing to find the quantity of
     * @return count of record with specified clothes type id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public Integer getClothesTypeCount(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        Integer count = 0;
        try {
            prSt = connection.prepareStatement(GET_CLOTHES_TYPE_COUNT);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return count;
    }

    /**
     * Method adding like to ad if like don't pressed, otherwise deleting like from ad
     *
     * @param adId   id of ad on which need add/delete like
     * @param userId id of user whose put/remove like
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void changeLikes(Integer adId, Integer userId) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            if (isLike(adId, userId)) {
                prSt = connection.prepareStatement(DELETE_LIKE);
            } else {
                prSt = connection.prepareStatement(ADD_LIKE);
            }
            prSt.setInt(1, adId);
            prSt.setInt(2, userId);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method that checks whether the user liked the ad or not
     *
     * @param adId   id of ad on which need to check pressed like or not
     * @param userId id of user whose pressed like or not
     * @return {@code true} if like pressed, otherwise {@code false}
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public boolean isLike(Integer adId, Integer userId) throws DaoException, ConnectionPoolException {
        boolean isLike = false;
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(IS_LIKE);
            prSt.setInt(1, adId);
            prSt.setInt(2, userId);

            ResultSet resSet = prSt.executeQuery();
            if (resSet.next()) {
                isLike = true;
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return isLike;
    }

    /**
     * Method for searching for records about ads with the specified ID and returning their number
     *
     * @param adId id of ad
     * @return count of record with specified ad id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public Integer getLikesCount(Integer adId) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        Integer count = 0;
        try {
            prSt = connection.prepareStatement(GET_LIKES_COUNT);
            prSt.setInt(1, adId);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return count;
    }
}