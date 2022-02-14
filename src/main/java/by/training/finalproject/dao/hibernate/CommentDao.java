package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.bean.Comments;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CommentDao extends AbstractDao<Integer, Comments> {
    private static final String GET_ALL = "SELECT * FROM comments";
    private static final String GET_ALL_AD_COMMENTS = "SELECT * FROM comments WHERE ad_info_id=:ad_id";
    private static final String GET_COMMENTS_COUNT = "SELECT ad_info_id, comment FROM comments WHERE ad_info_id=:ad_id";

    @Override
    public List<Comments> getAll() throws DaoException {
        try {
            return session.createNativeQuery(GET_ALL).addEntity(ClothesType.class).list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try {
            Comments comment = session.find(Comments.class, id);
            session.remove(comment);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public List<Comments> getAll(Integer adId) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_ALL_AD_COMMENTS).addEntity(Comments.class);
            query.setParameter("ad_id", adId);
            return query.list();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Integer getCommentCount(Integer adId) throws DaoException {
        try {
            Query query = session.createNativeQuery(GET_COMMENTS_COUNT);
            query.setParameter("ad_id", adId);
            return query.list().size();
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
