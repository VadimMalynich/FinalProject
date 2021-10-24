package by.training.finalproject.service;

import by.training.finalproject.bean.AdInfo;
import by.training.finalproject.bean.User;

import java.util.List;

public interface AdInfoService {
    /**
     * Method for get all necessary information about ads
     *
     * @return {@code List<AdInfo>} that contains all ads info
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    List<AdInfo> getAll() throws ServiceException;

    /**
     * Method for validating data before add ad in database
     *
     * @param adInfo info about ad
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void add(AdInfo adInfo) throws ServiceException;

    /**
     * Method deleting ad info from database
     *
     * @param id id of ad
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void delete(Integer id) throws ServiceException;

    /**
     * Method for validating data before edit ad in database
     *
     * @param newAdInfo information about the ad that will overwrite the data in the database
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void edit(AdInfo newAdInfo) throws ServiceException;

    /**
     * Method getting all ad info for show to client
     *
     * @param id of ad
     * @return {@code AdInfo} all necessary about ad
     * @throws ServiceException when the error occurred on the dao layer
     */
    AdInfo getAdInfo(Integer id) throws ServiceException;

    /**
     * Method for getting user ads
     *
     * @param userId id of user
     * @return {@code List<AdInfo>} with user ads
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<AdInfo> getUserAds(Integer userId) throws ServiceException;

    /**
     * Method for search ads by ad topic
     *
     * @param user user info
     * @param flag {@code boolean} value for define how to search ads
     * @param text the text that the user entered for the search
     * @return {@code List<AdInfo>} with ads that satisfy the condition
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<AdInfo> searchAds(String text, boolean flag, User user) throws ServiceException;

    /**
     * Method for filter ads by clothes category
     *
     * @param typeId id of clothes types
     * @return {@code List<AdInfo>} with ads that satisfy the condition
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<AdInfo> filterByType(Integer typeId) throws ServiceException;

    /**
     * Method adding like to ad if like don't pressed, otherwise deleting like from ad
     *
     * @param adId   id of ad on which need add/delete like
     * @param userId id of user whose put/remove like
     * @throws ServiceException when the error occurred on the dao layer
     */
    void changeLikesCounter(Integer adId, Integer userId) throws ServiceException;

    /**
     * Method that checks whether the user liked the ad or not
     *
     * @param adId   id of ad on which need to check pressed like or not
     * @param userId id of user whose pressed like or not
     * @return {@code true} if like pressed, otherwise {@code false}
     * @throws ServiceException when the error occurred on the dao layer
     */
    boolean isLikePressed(Integer adId, Integer userId) throws ServiceException;
}
