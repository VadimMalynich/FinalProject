package by.training.finalproject.dao.hibernate;

import by.training.finalproject.bean.*;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HibernateAdDao {
     List<AdInfo> getAll(Session session) throws DaoException;

     void delete(Integer id, Session session) throws DaoException;

     void add(AdInfo adInfo, Session session) throws DaoException;

    /**
     * Method edit data about ad in database
     *
     * @param adInfo object with new ad info that replace data in database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     void edit(AdInfo adInfo, Session session) throws DaoException;

    /**
     * Method get ad info from database
     *
     * @param id of record which need to read in database
     * @return object of {@code AdInfo} filled with data
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     AdInfo getAdInfo(Integer id, Session session) throws DaoException;

    /**
     * Method that read and return all ads that user put
     *
     * @param id of user whose ads we need to read
     * @return {@code List} of user ad objects
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     List<AdInfo> getUserAds(Integer id, Session session) throws DaoException;

    /**
     * Method for search ads by ad topic
     *
     * @param text   the text that the user entered for the search
     * @return {@code List<AdInfo>} with ads that satisfy the condition
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    List<AdInfo> searchAds(String text, Session session) throws DaoException;

    /**
     * Method for search ads by ad topic
     *
     * @param userId id of user
     * @param text   the text that the user entered for the search
     * @return {@code List<AdInfo>} with ads that satisfy the condition
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    List<AdInfo> searchAdsProfile(String text, Integer userId, Session session) throws DaoException;

    /**
     * Method for filter ads by clothes category
     *
     * @param typeId id of clothes types
     * @return {@code List<AdInfo>} with ads that satisfy the condition
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     List<AdInfo> filterByType(Integer typeId, Session session) throws DaoException;

    /**
     * Method for searching for ads with specified id and returning their number
     *
     * @param id of the type of clothing to find the quantity of
     * @return count of record with specified clothes type id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     Integer getClothesTypeCount(Integer id, Session session) throws DaoException;

    /**
     * Method adding like to ad if like don't pressed, otherwise deleting like from ad
     *
     * @param adId   id of ad on which need add/delete like
     * @param userId id of user whose put/remove like
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     void changeLikes(Integer adId, Integer userId, Session session) throws DaoException;

    /**
     * Method that checks whether the user liked the ad or not
     *
     * @param adId   id of ad on which need to check pressed like or not
     * @param userId id of user whose pressed like or not
     * @return {@code true} if like pressed, otherwise {@code false}
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     boolean isLike(Integer adId, Integer userId, Session session) throws DaoException;

    /**
     * Method for searching for records about ads with the specified ID and returning their number
     *
     * @param adId id of ad
     * @return count of record with specified ad id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
     Integer getLikesCount(Integer adId, Session session) throws DaoException;
}
