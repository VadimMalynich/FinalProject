package by.training.finalproject.dao;

import by.training.finalproject.bean.Comments;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends AbstractDao<Integer, Comments> {
    private static final String GET_ALL_COMMENTS = "SELECT ad_info_id, user_id, comment, comment_date FROM comments";
    private static final String GET_ALL_AD_COMMENTS = "SELECT id, ad_info_id, user_id, comment, comment_date FROM comments WHERE ad_info_id=?";
    private static final String DELETE_COMMENT = "DELETE FROM comments WHERE id=?";
    private static final String ADD_COMMENT = "INSERT INTO comments (ad_info_id, user_id, comment, comment_date) VALUES (?,?,?,?)";
    private static final String GET_COMMENTS_COUNT = "SELECT ad_info_id, comment FROM comments WHERE ad_info_id=?";

    @Override
    public List<Comments> getAll() throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<Comments> comments = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_COMMENTS);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                Comments comment = new Comments(resSet.getInt(1), resSet.getInt(2),
                        resSet.getString(3), resSet.getTimestamp(4));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return comments;
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(DELETE_COMMENT);
            prSt.setInt(1, id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    @Override
    public void add(Comments comment) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(ADD_COMMENT);
            prSt.setInt(1, comment.getAdId());
            prSt.setInt(2, comment.getUser().getId());
            prSt.setString(3, comment.getCommentText());
            prSt.setTimestamp(4, comment.getCommentDate());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method gets all needed data from database and put into entity class. Then collect entity classes into {@code List}
     *
     * @param adId id of ad for which will be got comments
     * @return {@code List} of objects that stored data from database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public List<Comments> getAll(Integer adId) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<Comments> comments = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_AD_COMMENTS);
            prSt.setInt(1, adId);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                Comments comment = new Comments(resSet.getInt(1), resSet.getInt(2), resSet.getInt(3),
                        resSet.getString(4), resSet.getTimestamp(5));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return comments;
    }

    /**
     * Method for searching for comments with specified ad id and returning their number
     *
     * @param adId id of ad
     * @return count of record with specified ad id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public Integer getCommentCount(Integer adId) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        Integer count = 0;
        try {
            prSt = connection.prepareStatement(GET_COMMENTS_COUNT);
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
