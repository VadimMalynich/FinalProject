package by.training.finalproject.service.hibernate;

import by.training.finalproject.bean.Comments;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.CommentDao;
import by.training.finalproject.dao.hibernate.HibernateDaoFactory;
import by.training.finalproject.dao.hibernate.utilities.SessionUtil;
import by.training.finalproject.service.CommentService;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.validation.CommentValidator;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class CommentServiceImpl extends SessionUtil implements CommentService {
    @Override
    public List<Comments> getAll(Integer adId) throws ServiceException {
        if (adId == null || adId < 1) {
            throw new ServiceException("Wrong adId for get comments");
        }
        List<Comments> comments;
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            commentDao.setSession(getSession());
            comments = commentDao.getAll(adId);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return comments;
    }

    @Override
    public void add(Comments comment) throws ServiceException {
        if (comment == null) {
            throw new ServiceException("Comment doesn't exist");
        }
        validateComment(comment);
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            commentDao.setSession(getSession());
            Timestamp commentDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
            comment.setCommentDate(commentDate);
            commentDao.add(comment);
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
            throw new ServiceException("Wrong id for delete comment");
        }
        CommentDao commentDao = HibernateDaoFactory.getInstance().getCommentDao();
        try {
            openTransactionSession();
            commentDao.setSession(getSession());
            commentDao.delete(id);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
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