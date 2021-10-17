package by.training.finalproject.service;

import by.training.finalproject.bean.Comments;

import java.util.List;

public interface CommentService {
    /**
     * Method for getting all ad comments
     *
     * @param adId id of ad
     * @return {@code List<Comments>} all comments that refer to needed ad
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<Comments> getAll(Integer adId) throws ServiceException;

    /**
     * Method for validating data before add comment in database
     *
     * @param comment comment info
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void add(Comments comment) throws ServiceException;

    /**
     * Method for deleting comment from database
     *
     * @param id id of comment
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void delete(Integer id) throws ServiceException;
}
