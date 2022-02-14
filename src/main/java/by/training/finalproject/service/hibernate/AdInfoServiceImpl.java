package by.training.finalproject.service.hibernate;


import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.User;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.AdDao;
import by.training.finalproject.dao.hibernate.CommentDao;
import by.training.finalproject.dao.hibernate.HibernateDaoFactory;
import by.training.finalproject.dao.hibernate.utilities.SessionUtil;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.validation.AdValidator;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class AdInfoServiceImpl extends SessionUtil implements AdInfoService {
    private static final String DIR_PATH = "/resources/images/adsPhoto/";

    @Override
    public List<AdInfo> getAll() throws ServiceException {
        List<AdInfo> list;
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            commentDao.setSession(getSession());
            list = adDao.getAll();
            if (!list.isEmpty()) {
                for (AdInfo adInfo : list) {
                    if (!adInfo.getAd().getPicture().contains(DIR_PATH)) {
                        adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                    }
                    adInfo.setLikesCount(adDao.getLikesCount(adInfo.getId()));
                    adInfo.setCommentsCount(commentDao.getCommentCount(adInfo.getId()));
                }
            }
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return list;
    }

    @Override
    public void add(AdInfo adInfo) throws ServiceException {
        if (adInfo == null) {
            throw new ServiceException("No info about ad");
        }
        validateAdData(adInfo);
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            if (adInfo.getAd().getPicture() == null) {
                adInfo.getAd().setPicture("default.png");
            }
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            adInfo.setDate(date);
            adDao.add(adInfo);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Wrong id for delete ad");
        }
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            adDao.delete(id);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public void edit(AdInfo newAdInfo) throws ServiceException {
        if (newAdInfo == null) {
            throw new ServiceException("Ad info doesn't exist");
        }
        validateAdData(newAdInfo);
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            newAdInfo.setDate(date);
            if (newAdInfo.getAd().getPicture().contains("/")) {
                String temp = newAdInfo.getAd().getPicture();
                newAdInfo.getAd().setPicture(temp.substring(temp.lastIndexOf("/") + 1));
            }
            adDao.edit(newAdInfo);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public AdInfo getAdInfo(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Wrong id for load ad info");
        }
        AdInfo adInfo;
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            commentDao.setSession(getSession());
            adInfo = adDao.getAdInfo(id);
            if(!adInfo.getAd().getPicture().contains(DIR_PATH)){
                adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
            }
            adInfo.setLikesCount(adDao.getLikesCount(id));
            adInfo.setCommentsCount(commentDao.getCommentCount(id));
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return adInfo;
    }

    @Override
    public List<AdInfo> getUserAds(Integer userId) throws ServiceException {
        if (userId == null || userId < 1) {
            throw new ServiceException("Wrong user id for getting user ads");
        }
        List<AdInfo> list;
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            commentDao.setSession(getSession());
            list = adDao.getUserAds(userId);
            if (!list.isEmpty()) {
                for (AdInfo adInfo : list) {
                    if(!adInfo.getAd().getPicture().contains(DIR_PATH)){
                        adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                    }
                    adInfo.setLikesCount(adDao.getLikesCount(adInfo.getId()));
                    adInfo.setCommentsCount(commentDao.getCommentCount(adInfo.getId()));
                }
            }
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return list;
    }

    @Override
    public List<AdInfo> searchAds(String text, boolean flag, User user) throws ServiceException {
        if (text == null || "".equals(text)) {
            throw new ServiceException("Wrong search text");
        }
        if (!flag && (user == null || user.getId() < 1)) {
            throw new ServiceException("Wrong user id");
        }
        List<AdInfo> list;
        text = "%" + text + "%";
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            commentDao.setSession(getSession());
            if (flag) {
                list = adDao.searchAds(text);
            } else {
                list = adDao.searchAdsProfile(text, user.getId());
            }
            if (!list.isEmpty()) {
                for (AdInfo adInfo : list) {
                    if(!adInfo.getAd().getPicture().contains(DIR_PATH)){
                        adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                    }
                    adInfo.setLikesCount(adDao.getLikesCount(adInfo.getId()));
                    adInfo.setCommentsCount(commentDao.getCommentCount(adInfo.getId()));
                }
            }
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return list;
    }

    @Override
    public List<AdInfo> filterByType(Integer typeId) throws ServiceException {
        if (typeId == null || typeId < 1) {
            throw new ServiceException("Wrong type id");
        }
        List<AdInfo> list;
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            commentDao.setSession(getSession());
            list = adDao.filterByType(typeId);
            if (!list.isEmpty()) {
                for (AdInfo adInfo : list) {
                    if(!adInfo.getAd().getPicture().contains(DIR_PATH)){
                        adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                    }
                    adInfo.setLikesCount(adDao.getLikesCount(adInfo.getId()));
                    adInfo.setCommentsCount(commentDao.getCommentCount(adInfo.getId()));
                }
            }
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return list;
    }

    @Override
    public void changeLikesCounter(Integer adId, Integer userId) throws ServiceException {
        if (adId == null || adId < 1 || userId == null || userId < 1) {
            throw new ServiceException("Wrong user or ad id");
        }
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            adDao.changeLikes(adId, userId);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public boolean isLikePressed(Integer adId, Integer userId) throws ServiceException {
        if (adId == null || adId < 1 || userId == null || userId < 1) {
            throw new ServiceException("Wrong user or ad id");
        }
        boolean flag;
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        try {
            openTransactionSession();
            adDao.setSession(getSession());
            flag = adDao.isLike(adId, userId);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return flag;
    }

    private void validateAdData(AdInfo adInfo) throws ServiceException {
        if (!AdValidator.validateTopic(adInfo.getAd().getTopic())) {
            throw new ServiceException("Wrong topic");
        }
        if (!AdValidator.validateMaterial(adInfo.getAd().getMaterial())) {
            throw new ServiceException("Wrong material");
        }
        if (!AdValidator.validateDescription(adInfo.getAd().getDescription())) {
            throw new ServiceException("Wrong description");
        }
        if (!AdValidator.validatePicture(adInfo.getAd().getPicture())) {
            throw new ServiceException("Wrong picture path");
        }
    }
}