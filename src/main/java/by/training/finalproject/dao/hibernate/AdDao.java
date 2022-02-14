package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.query.Query;

import java.util.List;

public class AdDao extends AbstractDao<Integer, AdInfo> {
    private static final String GET_ALL = "SELECT * FROM ads_info ORDER BY date DESC";
    private static final String GET_USER_ADS = "SELECT * FROM ads_info WHERE user_id=:user_id";
    private static final String SEARCH_ADS = "SELECT * FROM ads_info where topic like ";
    private static final String SEARCH_USER_ADS = "SELECT * FROM ads_info where user_id=:user_id AND topic like ";
    private static final String FILTER_BY_TYPE = "SELECT * FROM ads_info where category_id=:category_id";
    private static final String GET_CLOTHES_TYPE_COUNT = "SELECT * FROM ads_info WHERE category_id=:category_id";
    private static final String DELETE_LIKE = "DELETE FROM likes WHERE ad_info_id=:ad_id AND user_id=:user_id";
    private static final String ADD_LIKE = "INSERT INTO likes (ad_info_id, user_id) VALUES (:ad_id,:user_id)";
    private static final String IS_LIKE = "SELECT * FROM likes WHERE ad_info_id=:ad_id AND user_id=:user_id";
    private static final String GET_LIKES_COUNT = "SELECT * FROM likes WHERE ad_info_id=:ad_id";

    @Override
    public List<AdInfo> getAll() throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(AdInfo.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try {
            AdInfo adInfo = session.find(AdInfo.class, id);
            session.remove(adInfo);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public AdInfo getAdInfo(Integer id) throws DaoException {
        try {
            return session.find(AdInfo.class, id);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<AdInfo> getUserAds(Integer id) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_ADS).addEntity(AdInfo.class);
            query.setParameter("user_id", id);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<AdInfo> searchAds(String text) throws DaoException {
        try {
            Query query = session.createNativeQuery(SEARCH_ADS + text).addEntity(AdInfo.class);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<AdInfo> searchAdsProfile(String text, Integer userId) throws DaoException {
        try {
            Query query = session.createNativeQuery(SEARCH_USER_ADS + text).addEntity(AdInfo.class);
            query.setParameter("user_id", userId);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<AdInfo> filterByType(Integer typeId) throws DaoException {
        try {
            Query query = session.createNativeQuery(FILTER_BY_TYPE).addEntity(AdInfo.class);
            query.setParameter("category_id", typeId);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Integer getClothesTypeCount(Integer id) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_CLOTHES_TYPE_COUNT);
            query.setParameter("category_id", id);
            return query.list().size();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public void changeLikes(Integer adId, Integer userId) throws DaoException {
        if (isLike(adId, userId)) {
            Query query = session.createNativeQuery(ADD_LIKE);
            query.setParameter("ad_id", adId);
            query.setParameter("user_id", userId);
            query.executeUpdate();
        } else {
            Query query = session.createNativeQuery(DELETE_LIKE);
            query.setParameter("ad_id", adId);
            query.setParameter("user_id", userId);
            query.executeUpdate();
        }
    }

    public boolean isLike(Integer adId, Integer userId) throws DaoException {
        try {
            Query query = session.createNativeQuery(IS_LIKE);
            query.setParameter("ad_id", adId);
            query.setParameter("user_id", userId);
            return query.list().isEmpty();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Integer getLikesCount(Integer adId) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_LIKES_COUNT);
            query.setParameter("ad_id", adId);
            return query.list().size();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
