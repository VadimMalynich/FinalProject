package by.training.finalproject.service.impl;

import by.training.finalproject.bean.Comments;
import by.training.finalproject.bean.User;
import by.training.finalproject.dao.CommentDao;
import by.training.finalproject.dao.DaoFactory;
import by.training.finalproject.dao.UserDao;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.transaction.Transaction;
import by.training.finalproject.dao.transaction.TransactionFactory;
import by.training.finalproject.service.CommentService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.validation.CommentValidator;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comments> getAll(Integer adId) throws ServiceException {
        if (adId == null || adId < 1) {
            throw new ServiceException("Wrong adId for get comments");
        }
        List<Comments> comments;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            CommentDao commentDao = daoFactory.getCommentDao();
            UserDao userDao = daoFactory.getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(commentDao, userDao);
                comments = commentDao.getAll(adId);
                for (Comments comment : comments) {
                    User userInfo = comment.getUser();
                    userInfo.setLogin(userDao.getUserLogin(userInfo.getId()));
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
        return comments;
    }

    @Override
    public void add(Comments comment) throws ServiceException {
        if (comment == null) {
            throw new ServiceException("Comment doesn't exist");
        }
        try {
            validateComment(comment);
            CommentDao commentDao = DaoFactory.getInstance().getCommentDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(commentDao);
                Timestamp commentDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
                comment.setCommentDate(commentDate);
                commentDao.add(comment);
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
        if (id == null || id < 1) {
            throw new ServiceException("Wrong id for delete comment");
        }
        try {
            CommentDao commentDao = DaoFactory.getInstance().getCommentDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(commentDao);
                commentDao.delete(id);
                transaction.commit();
            } catch (DaoException | ConnectionPoolException e) {
                transaction.rollback();
                throw new ServiceException(e);
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    private void validateComment(Comments comment) throws ServiceException {
        if (CommentValidator.validateId(comment.getUser().getId())) {
            throw new ServiceException("Wrong id");
        }
        if (CommentValidator.validateId(comment.getAdId())) {
            throw new ServiceException("Wrong id");
        }
        if (!CommentValidator.validateText(comment.getCommentText())) {
            throw new ServiceException("Wrong comment text");
        }
    }
}