package by.training.finalproject.service;


import by.training.finalproject.bean.City;
import by.training.finalproject.bean.User;

import java.util.List;

public interface UserService {
    /**
     * Method for getting all users
     *
     * @return {@code List<User>} with info that lies in database
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<User> getAllUser() throws ServiceException;

    /**
     * Method for checking user info which user entered, and if data will be correct add user into database
     *
     * @param user            all info that user entered
     * @param confirmPassword password confirmation
     * @param messengers      users messengers
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void registration(User user, String confirmPassword, String[] messengers) throws ServiceException;

    /**
     * Method for deleting user
     *
     * @param id id of user
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void delete(Integer id) throws ServiceException;

    /**
     * Method for validating data before edit user in database
     *
     * @param user       new info about user
     * @param messengers messengers selected by the user
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void edit(User user, String[] messengers) throws ServiceException;


    /**
     * Method for validating data before edit user in database
     *
     * @param user       new info about user
     * @param messengers messengers selected by the user
     * @param oldPass    password from the database
     * @param newPass    password for re-write password in database
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    void edit(User user, String[] messengers, String oldPass, String newPass) throws ServiceException;

    /**
     * Method for validating data before getting user info from database
     *
     * @param login    that user entered
     * @param password that user entered
     * @return {@code User} class with all user info
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    User authorization(String login, String password) throws ServiceException;

    /**
     * Method for getting user info
     *
     * @param id user id
     * @return object of {@code User} with needed info
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    User getUser(Integer id) throws ServiceException;

    /**
     * Method for getting needed ad user info for ad
     *
     * @param id id of user
     * @return {@code User} class with necessary user info
     * @throws ServiceException when the error occurred on the dao layer or when validate data
     */
    User getUserAdInfo(Integer id) throws ServiceException;

    /**
     * Method for getting info about all cities that user can choose
     *
     * @return {@code List<City>} with info that lies in database
     * @throws ServiceException when the error occurred on the dao layer
     */
    List<City> getAllCities() throws ServiceException;

}
