package by.training.finalproject.service.jdbc;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.exception.*;
import by.training.finalproject.dao.jbdc.AdDao;
import by.training.finalproject.dao.jbdc.ClothesTypeDao;
import by.training.finalproject.dao.jbdc.DaoFactory;
import by.training.finalproject.dao.jbdc.transaction.Transaction;
import by.training.finalproject.dao.jbdc.transaction.TransactionFactory;
import by.training.finalproject.service.ClothesTypeService;
import by.training.finalproject.service.ServiceException;

import java.util.List;

public class ClothesTypeServiceImpl implements ClothesTypeService {

    @Override
    public List<ClothesType> getAll() throws ServiceException {
        List<ClothesType> list;
        try {
            DaoFactory factory = DaoFactory.getInstance();
            ClothesTypeDao clothesTypeDao = factory.getClothesTypeDao();
            AdDao adDao = factory.getAdInfoDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(clothesTypeDao, adDao);
                list = clothesTypeDao.getAll();
                for (ClothesType l : list) {
                    l.setCount(adDao.getClothesTypeCount(l.getId()));
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
    public void add(ClothesType clothesType) throws ServiceException {
        validateData(clothesType);
        try {
            ClothesTypeDao clothesTypeDao = DaoFactory.getInstance().getClothesTypeDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(clothesTypeDao);
                clothesTypeDao.add(clothesType);
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
    public void delete(Integer id) throws ServiceException {
        if (id == null || id <= 1) {
            throw new ServiceException("Invalid category id for delete!");
        }
        try {
            ClothesTypeDao clothesTypeDao = DaoFactory.getInstance().getClothesTypeDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(clothesTypeDao);
                clothesTypeDao.delete(id);
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
    public void edit(ClothesType clothesType) throws ServiceException {
        validateData(clothesType);
        try {
            ClothesTypeDao clothesTypeDao = DaoFactory.getInstance().getClothesTypeDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(clothesTypeDao);
                clothesTypeDao.edit(clothesType);
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
    public String getClothesCategory(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Impossible to get category name!");
        }
        String str;
        try {
            ClothesTypeDao clothesTypeDao = DaoFactory.getInstance().getClothesTypeDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(clothesTypeDao);
                str = clothesTypeDao.getClothesCategory(id);
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
