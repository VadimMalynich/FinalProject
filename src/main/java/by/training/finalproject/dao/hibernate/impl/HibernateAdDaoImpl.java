package by.training.finalproject.dao.hibernate.impl;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.HibernateAdDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateAdDaoImpl implements HibernateAdDao {
    private static final String GET_ALL = "SELECT * FROM ads_info ORDER BY date DESC";
    private static final String DELETE_AD = "DELETE FROM ads_info WHERE id=";
    private static final String ADD_AD = "INSERT INTO ads_info (user_id, category_id, date, topic, material, size, sex, description, picture) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String EDIT_AD = "UPDATE ads_info SET category_id=?, date=?, topic=?, material=?, size=?, sex=?, description=?, picture=? WHERE id=?";
    private static final String GET_AD_INFO = "SELECT user_id, category_id, date, topic, material, size, sex, description, picture FROM ads_info WHERE id=?";
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
    public List<AdInfo> getAll(Session session) throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(AdInfo.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }

    }

    @Override
    public void delete(Integer id, Session session) throws DaoException {
        try {
            AdInfo adInfo = session.find(AdInfo.class, id);
            session.remove(adInfo);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }


//        session.createNativeQuery(DELETE_AD + id).executeUpdate();
//        SessionUtil sessionUtil = new SessionUtil();
//        sessionUtil.openTransactionSession();
//        AdInfo adInfo = sessionUtil.getSession().find(AdInfo.class, id);
//        sessionUtil.getSession().detach(adInfo);
//        sessionUtil.getSession().remove(adInfo);
//        System.err.println("//////////////////////////////////");
//        sessionUtil.commitTransactionSession();
//        System.err.println("+++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void add(AdInfo adInfo, Session session) throws DaoException {
        try {
            session.save(adInfo);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void edit(AdInfo adInfo, Session session) throws DaoException {
        try {
            session.update(adInfo);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public AdInfo getAdInfo(Integer id, Session session) throws DaoException {
        try {
            return session.find(AdInfo.class, id);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<AdInfo> getUserAds(Integer id, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_USER_ADS).addEntity(AdInfo.class);
            query.setParameter("user_id", id);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<AdInfo> searchAds(String text, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(SEARCH_ADS + text).addEntity(AdInfo.class);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<AdInfo> searchAdsProfile(String text, Integer userId, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(SEARCH_USER_ADS + text).addEntity(AdInfo.class);
            query.setParameter("user_id", userId);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }


    @Override
    public List<AdInfo> filterByType(Integer typeId, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(FILTER_BY_TYPE).addEntity(AdInfo.class);
            query.setParameter("category_id", typeId);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Integer getClothesTypeCount(Integer id, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_CLOTHES_TYPE_COUNT);
            query.setParameter("category_id", id);
            return query.list().size();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void changeLikes(Integer adId, Integer userId, Session session) throws DaoException {
        if (isLike(adId, userId, session)) {
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

    @Override
    public boolean isLike(Integer adId, Integer userId, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(IS_LIKE);
            query.setParameter("ad_id", adId);
            query.setParameter("user_id", userId);
            return query.list().isEmpty();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Integer getLikesCount(Integer adId, Session session) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_LIKES_COUNT);
            query.setParameter("ad_id", adId);
            return query.list().size();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
