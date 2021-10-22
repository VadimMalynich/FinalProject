package by.training.finalproject.dao;

import by.training.finalproject.bean.*;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<Integer, User> {
    private static final String GET_ALL_USERS = "SELECT id, email, password, name, phone, role, city_id FROM users";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String ADD_USER = "INSERT INTO users (email, password, name, phone, city_id) VALUES (?,?,?,?,?)";
    private static final String EDIT_USER = "UPDATE users SET email=?, password=?, name=?, phone=?, role=?, city_id=? WHERE id=?";
    private static final String AUTHORIZATION = "SELECT id, email, password, name, phone, role, city_id FROM users WHERE email=?";
    private static final String GET_USER_INFO = "SELECT email, password, name, phone, role, city_id FROM users WHERE id=?";
    private static final String GET_USER_AD_INFO = "SELECT name, phone, city_id FROM users WHERE id=?";
    private static final String GET_USER_LOGIN = "SELECT email FROM users WHERE id=?";
    private static final String GET_USER_PASSWORD = "SELECT password FROM users WHERE email=?";
    private static final String GET_USER_ROLE = "SELECT role FROM users WHERE id=?";
    private static final String GET_USER_ID = "SELECT id FROM users WHERE email=?";
    private static final String GET_USER_MESSENGERS = "SELECT telegram, viber, whatsapp FROM messengers WHERE user_id = ?";
    private static final String ADD_MESSENGERS = "INSERT INTO messengers (user_id, telegram, viber, whatsapp) VALUES (?,?,?,?)";
    private static final String EDIT_MESSENGERS = "UPDATE messengers SET telegram=?, viber=?, whatsapp=? WHERE user_id=?";
    private static final String GET_ALL_CITIES = "SELECT id, region, city FROM cities";
    private static final String GET_ALL_CITIES_BY_REGION = "SELECT id, region, city FROM cities WHERE region = ?";
    private static final String GET_CITY_NAME = "SELECT region, city FROM cities WHERE id = ?";

    @Override
    public List<User> getAll() throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<User> users = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_USERS);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                User user = new User(resSet.getInt(1), resSet.getString(2), resSet.getString(3),
                        resSet.getString(4), resSet.getString(5),
                        UserRole.getByCode(resSet.getInt(6)));
                user.setMessengers(getUserMessengers(user.getId()));
                user.setCity(getCityInfo(resSet.getInt(7)));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return users;
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(DELETE_USER);
            prSt.setInt(1, id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    @Override
    public void add(User user) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getName());
            prSt.setString(4, user.getPhoneNumber());
            prSt.setInt(5, user.getCity().getId());
            prSt.executeUpdate();
            ResultSet resultSet = prSt.getGeneratedKeys();
            if (resultSet.next()) {
                addMessengers(resultSet.getInt(1), user.getMessengers());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method edit info about user in database
     *
     * @param user object of {@code User} with new user info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void edit(User user) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(EDIT_USER);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getName());
            prSt.setString(4, user.getPhoneNumber());
            prSt.setInt(5, user.getRole().getValue());
            prSt.setInt(6, user.getCity().getId());
            prSt.setInt(7, user.getId());
            editMessengers(user.getId(), user.getMessengers());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method for getting user info
     *
     * @param login user login
     * @return object of {@code User} with filled data
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public User authorization(String login) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        User user = null;
        try {
            prSt = connection.prepareStatement(AUTHORIZATION);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                user = new User(resSet.getInt(1), resSet.getString(2), resSet.getString(3),
                        resSet.getString(4), resSet.getString(5),
                        UserRole.getByCode(resSet.getInt(6)));
                user.setMessengers(getUserMessengers(user.getId()));
                user.setCity(getCityInfo(resSet.getInt(7)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return user;
    }

    /**
     * Method for getting user info
     *
     * @param id user id
     * @return object of {@code User} with needed info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public User getUserInfo(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        User user = null;
        try {
            prSt = connection.prepareStatement(GET_USER_INFO);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                user = new User(id, resSet.getString(1), resSet.getString(2),resSet.getString(3),
                        resSet.getString(4), UserRole.getByCode(resSet.getInt(5)),
                        getCityInfo(resSet.getInt(6)), getUserMessengers(id));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return user;
    }

    /**
     * Method for getting user info that needed for displaying in ad
     *
     * @param id id of ad
     * @return object of {@code User} with the necessary data filled in
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public User getUserAdInfo(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        User user = null;
        try {
            prSt = connection.prepareStatement(GET_USER_AD_INFO);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                user = new User(id, resSet.getString(1), resSet.getString(2),
                        getCityInfo(resSet.getInt(3)), getUserMessengers(id));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return user;
    }

    /**
     * Method for getting user login by user id
     *
     * @param id user id
     * @return user login
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public String getUserLogin(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        String login = null;
        try {
            prSt = connection.prepareStatement(GET_USER_LOGIN);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                login = resSet.getString(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return login;
    }

    /**
     * Method for getting user login
     *
     * @param login user login
     * @return user password
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public String getUserPassword(String login) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        String password = null;
        try {
            prSt = connection.prepareStatement(GET_USER_PASSWORD);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                password = resSet.getString(1);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return password;
    }

    /**
     * Method for getting user role
     *
     * @param id id of user
     * @return {@code Integer} value of role
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public Integer getUserRole(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        Integer role = null;
        try {
            prSt = connection.prepareStatement(GET_USER_ROLE);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                role = resSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return role;
    }

    /**
     * Method for getting user id by user login
     *
     * @param login user login
     * @return user id
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public Integer getUserId(String login) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        Integer id = null;
        try {
            prSt = connection.prepareStatement(GET_USER_ID);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                id = resSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return id;
    }

    /**
     * Method of receiving the user's messengers
     *
     * @param id id of user
     * @return object of {@code Messengers} read from database
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public Messengers getUserMessengers(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        Messengers messengers = new Messengers();
        try {
            prSt = connection.prepareStatement(GET_USER_MESSENGERS);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                messengers = new Messengers(resSet.getBoolean(1), resSet.getBoolean(2), resSet.getBoolean(3));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return messengers;
    }

    /**
     * Method of adding the user's messengers to the database
     *
     * @param userId     id of user
     * @param messengers object of {@code Messengers} with info that will be added
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void addMessengers(Integer userId, Messengers messengers) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(ADD_MESSENGERS);
            prSt.setInt(1, userId);
            prSt.setBoolean(2, messengers.isTelegram());
            prSt.setBoolean(3, messengers.isViber());
            prSt.setBoolean(4, messengers.isWhatsapp());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    /**
     * Method edit user messengers info
     *
     * @param userId     id of user
     * @param messengers object of {@code Messengers} with new info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public void editMessengers(Integer userId, Messengers messengers) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        try {
            prSt = connection.prepareStatement(EDIT_MESSENGERS);
            prSt.setBoolean(1, messengers.isTelegram());
            prSt.setBoolean(2, messengers.isViber());
            prSt.setBoolean(3, messengers.isWhatsapp());
            prSt.setInt(4, userId);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
    }

    public List<City> getAllCities() throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<City> cities = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_CITIES);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                City city = new City();
                city.setId(resSet.getInt(1));
                city.setRegion(Region.getByCode(resSet.getInt(2)));
                city.setName(resSet.getString(3));
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return cities;
    }

    /**
     * Method for getting all cities that relate for definite region
     *
     * @param regionId id of region
     * @return {@code List} of {@code City} objects that relate to specified region
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public List<City> getAllByRegion(Integer regionId) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        List<City> cities = new ArrayList<>();
        try {
            prSt = connection.prepareStatement(GET_ALL_CITIES_BY_REGION);
            prSt.setInt(1, regionId);
            resSet = prSt.executeQuery();
            while (resSet.next()) {
                City city = new City();
                city.setId(resSet.getInt(1));
                city.setRegion(Region.getByCode(resSet.getInt(2)));
                city.setName(resSet.getString(3));
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return cities;
    }

    /**
     * Method that find city by id and return city info
     *
     * @param id id of city
     * @return {@code City} city info
     * @throws DaoException            if an error occurs when reading data from the database
     * @throws ConnectionPoolException when an error occurs when closing {@code Statement}
     */
    public City getCityInfo(Integer id) throws DaoException, ConnectionPoolException {
        PreparedStatement prSt = null;
        ResultSet resSet;
        City city = null;
        try {
            prSt = connection.prepareStatement(GET_CITY_NAME);
            prSt.setInt(1, id);
            resSet = prSt.executeQuery();
            if (resSet.next()) {

                city = new City(id, Region.getByCode(resSet.getInt(1)), resSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(prSt);
        }
        return city;
    }
}
