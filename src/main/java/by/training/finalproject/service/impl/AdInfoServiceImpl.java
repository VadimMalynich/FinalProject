package by.training.finalproject.service.impl;


import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.bean.User;
import by.training.finalproject.dao.*;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.transaction.Transaction;
import by.training.finalproject.dao.transaction.TransactionFactory;
import by.training.finalproject.service.AdInfoService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.validation.AdValidator;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class AdInfoServiceImpl implements AdInfoService {
    private static final String DIR_PATH = "/resources/images/adsPhoto/";

    @Override
    public List<AdInfo> getAll() throws ServiceException {
        List<AdInfo> list;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            AdDao adDao = daoFactory.getAdInfoDao();
            CommentDao commentDao = daoFactory.getCommentDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao, commentDao);
                list = adDao.getAll();
                if (!list.isEmpty()) {
                    for (AdInfo adInfo : list) {
                        adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                        adInfo.setLikesCount(adDao.getLikesCount(adInfo.getId()));
                        adInfo.setCommentsCount(commentDao.getCommentCount(adInfo.getId()));
                    }
                }
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return list;
    }

    @Override
    public void add(AdInfo adInfo) throws ServiceException {
        if (adInfo == null) {
            throw new ServiceException("No info about ad");
        }
        try {
            validateAdData(adInfo);
            AdDao adDao = DaoFactory.getInstance().getAdInfoDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao);
                if (adInfo.getAd().getPicture() == null) {
                    adInfo.getAd().setPicture("default.png");
                }
                Date date = new Date(Calendar.getInstance().getTimeInMillis());
                adInfo.setDate(date);
                adDao.add(adInfo);
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Wrong id for delete ad");
        }
        try {
            AdDao adDao = DaoFactory.getInstance().getAdInfoDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao);
                adDao.delete(id);
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void edit(AdInfo newAdInfo) throws ServiceException {
        if (newAdInfo == null) {
            throw new ServiceException("Ad info doesn't exist");
        }
        try {
            validateAdData(newAdInfo);
            AdDao adDao = DaoFactory.getInstance().getAdInfoDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao);
                Date date = new Date(Calendar.getInstance().getTimeInMillis());
                newAdInfo.setDate(date);
                if (newAdInfo.getAd().getPicture().contains("/")) {
                    String temp = newAdInfo.getAd().getPicture();
                    newAdInfo.getAd().setPicture(temp.substring(temp.lastIndexOf("/") + 1));
                }
                adDao.edit(newAdInfo);
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public AdInfo getAdInfo(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Wrong id for load ad info");
        }
        AdInfo adInfo;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            AdDao adDao = daoFactory.getAdInfoDao();
            CommentDao commentDao = daoFactory.getCommentDao();
            UserDao userDao = daoFactory.getUserDao();
            ClothesTypeDao clothesTypeDao = daoFactory.getClothesTypeDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao, commentDao, userDao, clothesTypeDao);
                adInfo = adDao.getAdInfo(id);
                adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                User user = userDao.getUserAdInfo(adInfo.getUserInfo().getId());
                ClothesType clothesType = adInfo.getCategoryInfo();
                clothesType.setCategory(clothesTypeDao.getClothesCategory(clothesType.getId()));
                adInfo.setUserInfo(user);
                adInfo.setCategoryInfo(clothesType);
                adInfo.setLikesCount(adDao.getLikesCount(id));
                adInfo.setCommentsCount(commentDao.getCommentCount(id));
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return adInfo;
    }

    @Override
    public List<AdInfo> getUserAds(Integer userId) throws ServiceException {
        if (userId == null || userId < 1) {
            throw new ServiceException("Wrong user id for getting user ads");
        }
        List<AdInfo> list;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            AdDao adDao = daoFactory.getAdInfoDao();
            CommentDao commentDao = daoFactory.getCommentDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao, commentDao);
                list = adDao.getUserAds(userId);
                if (!list.isEmpty()) {
                    for (AdInfo adInfo : list) {
                        adInfo.getAd().setPicture(DIR_PATH + adInfo.getAd().getPicture());
                        adInfo.setLikesCount(adDao.getLikesCount(adInfo.getId()));
                        adInfo.setCommentsCount(commentDao.getCommentCount(adInfo.getId()));
                    }
                }
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return list;
    }

    @Override
    public void changeLikesCounter(Integer adId, Integer userId) throws ServiceException {
        if (adId == null || adId < 1 || userId == null || userId < 1) {
            throw new ServiceException("Wrong user or ad id");
        }
        try {
            AdDao adDao = DaoFactory.getInstance().getAdInfoDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao);
                adDao.changeLikes(adId, userId);
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean isLikePressed(Integer adId, Integer userId) throws ServiceException {
        if (adId == null || adId < 1 || userId == null || userId < 1) {
            throw new ServiceException("Wrong user or ad id");
        }
        boolean flag;
        try {
            AdDao adDao = DaoFactory.getInstance().getAdInfoDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(adDao);
                flag = adDao.isLike(adId, userId);
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
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