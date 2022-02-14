package by.training.finalproject.service.hibernate;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.AdDao;
import by.training.finalproject.dao.hibernate.ClothesTypeDao;
import by.training.finalproject.dao.hibernate.HibernateDaoFactory;
import by.training.finalproject.dao.hibernate.utilities.SessionUtil;
import by.training.finalproject.service.ClothesTypeService;
import by.training.finalproject.service.ServiceException;

import java.util.List;

public class ClothesTypeServiceImpl extends SessionUtil implements ClothesTypeService {

    @Override
    public List<ClothesType> getAll() throws ServiceException {
        List<ClothesType> list;
        ClothesTypeDao clothesTypeDao = HibernateDaoFactory.getInstance().getClothesTypeDao();
        AdDao adDao = HibernateDaoFactory.getInstance().getAdDao();
        try {
            openTransactionSession();
            clothesTypeDao.setSession(getSession());
            adDao.setSession(getSession());
            list = clothesTypeDao.getAll();
            for (ClothesType l : list) {
                l.setCount(adDao.getClothesTypeCount(l.getId()));
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
    public void add(ClothesType clothesType) throws ServiceException {
        validateData(clothesType);
        ClothesTypeDao clothesTypeDao = HibernateDaoFactory.getInstance().getClothesTypeDao();
        try {
            openTransactionSession();
            clothesTypeDao.setSession(getSession());
            clothesTypeDao.add(clothesType);
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
        if (id == null || id <= 1) {
            throw new ServiceException("Invalid category id for delete!");
        }
        ClothesTypeDao clothesTypeDao = HibernateDaoFactory.getInstance().getClothesTypeDao();
        try {
            openTransactionSession();
            clothesTypeDao.setSession(getSession());
            clothesTypeDao.delete(id);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public void edit(ClothesType clothesType) throws ServiceException {
        validateData(clothesType);
        ClothesTypeDao clothesTypeDao = HibernateDaoFactory.getInstance().getClothesTypeDao();
        try {
            openTransactionSession();
            clothesTypeDao.setSession(getSession());
            clothesTypeDao.edit(clothesType);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public String getClothesCategory(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Impossible to get category name!");
        }
        String str;
        ClothesTypeDao clothesTypeDao = HibernateDaoFactory.getInstance().getClothesTypeDao();
        try {
            openTransactionSession();
            clothesTypeDao.setSession(getSession());
            str = clothesTypeDao.getClothesCategory(id);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return str;
    }

    private void validateData(ClothesType clothesType) throws ServiceException {
        if (clothesType == null) {
            throw new ServiceException("Clothes type doesn't exist");
        }
        if (clothesType.getCategory() == null || "".equals(clothesType.getCategory())) {
            throw new ServiceException("Wrong category name");
        }
        if (clothesType.getCategory().length() < 4) {
            throw new ServiceException("Incorrect category name");
        }
    }
}
