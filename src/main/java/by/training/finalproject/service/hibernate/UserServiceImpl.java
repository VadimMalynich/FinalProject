package by.training.finalproject.service.hibernate;

import by.training.finalproject.bean.City;
import by.training.finalproject.bean.Messengers;
import by.training.finalproject.bean.User;
import by.training.finalproject.bean.UserRole;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.CityDao;
import by.training.finalproject.dao.hibernate.HibernateDaoFactory;
import by.training.finalproject.dao.hibernate.UserDao;
import by.training.finalproject.dao.hibernate.utilities.SessionUtil;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.UserService;
import by.training.finalproject.service.validation.PBKDF2Hasher;
import by.training.finalproject.service.validation.UserValidator;

import java.util.List;

public class UserServiceImpl extends SessionUtil implements UserService {
    private static PBKDF2Hasher hasher = new PBKDF2Hasher(14);

    @Override
    public List<User> getAllUser() throws ServiceException {
        List<User> list;
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            list = userDao.getAll();
            for (User user : list) {
                user.setMessengers(userDao.getUserMessengers(user.getId()));
            }
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return list;
    }

    @Override
    public void registration(User user, String confirmPassword, String[] messengers) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Can not register user. User not exist.");
        }
        if (!user.getPassword().equals(confirmPassword)) {
            throw new ServiceException("Passwords don't match");
        }
        user.setMessengers(checkMessengers(messengers));
        validateUserData(user);
        user.setRole(UserRole.USER);
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            char[] pass = user.getPassword().toCharArray();
            user.setPassword(hasher.hash(pass));
            user.getMessengers().setId(userDao.addUser(user));
            userDao.addMessengers(user.getMessengers());
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
            throw new ServiceException("Wrong id for delete user");
        }
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            if (userDao.getUserRole(id) == 0) {
                throw new ServiceException("Cannot delete admin");
            }
            userDao.delete(id);
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public void edit(User user, String[] messengers) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Can not edit user");
        }
        validateEditUserData(user);
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            user.setMessengers(checkMessengers(messengers));
            userDao.edit(user);
            user.getMessengers().setId(user.getId());
            userDao.editMessengers(user.getMessengers());
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public void edit(User user, String[] messengers, String oldPass, String newPass) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Can not edit user");
        }
        validateEditUserData(user);
        validatePasswords(oldPass, newPass);
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            user.setMessengers(checkMessengers(messengers));
            char[] pass = oldPass.toCharArray();
            if (hasher.checkPassword(pass, user.getPassword())) {
                char[] newPassword = newPass.toCharArray();
                user.setPassword(hasher.hash(newPassword));
            } else {
                throw new ServiceException("Entered wrong old password");
            }
            userDao.edit(user);
            user.getMessengers().setId(user.getId());
            userDao.editMessengers(user.getMessengers());
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        validateAuthorizationData(login, password);
        User user;
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            String dbPass = userDao.getUserPassword(login);
            if (dbPass == null) {
                throw new ServiceException("Entered wrong login");
            }
            char[] pass = password.toCharArray();
            if (hasher.checkPassword(pass, dbPass)) {
                user = userDao.authorization(login);
                user.setMessengers(userDao.getUserMessengers(user.getId()));
            } else {
                throw new ServiceException("Entered wrong password");
            }
            commitTransactionSession();
        } catch (DaoException | IllegalArgumentException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return user;
    }

    @Override
    public User getUser(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Invalid ad id for take user info");
        }
        User user;
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            user = userDao.getUser(id);
            user.setMessengers(userDao.getUserMessengers(user.getId()));
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return user;
    }

    @Override
    public User getUserAdInfo(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Invalid ad id for take user info");
        }
        User user;
        UserDao userDao = HibernateDaoFactory.getInstance().getUserDao();
        try {
            openTransactionSession();
            userDao.setSession(getSession());
            user = userDao.getUserAdInfo(id);
            user.setMessengers(userDao.getUserMessengers(user.getId()));
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return user;
    }

    @Override
    public List<City> getAllCities() throws ServiceException {
        List<City> list;
        CityDao cityDao = HibernateDaoFactory.getInstance().getCityDao();
        try {
            openTransactionSession();
            cityDao.setSession(getSession());
            list = cityDao.getAll();
            commitTransactionSession();
        } catch (DaoException e) {
            rollbackTransactionSession();
            throw new ServiceException(e);
        } finally {
            closeSession();
        }
        return list;
    }

    private Messengers checkMessengers(String[] messengers) {
        Messengers returnMessengers = new Messengers();
        if (messengers != null) {
            for (String messenger : messengers) {
                switch (messenger) {
                    case "telegram":
                        returnMessengers.setTelegram(true);
                        break;
                    case "viber":
                        returnMessengers.setViber(true);
                        break;
                    case "whatsapp":
                        returnMessengers.setWhatsapp(true);
                        break;
                    default:
                        break;
                }
            }
        }
        return returnMessengers;
    }

    private void validatePasswords(String oldPass, String newPass) throws ServiceException {
        if (!UserValidator.validatePassword(oldPass)) {
            throw new ServiceException("Wrong old password");
        }
        if (!UserValidator.validatePassword(newPass)) {
            throw new ServiceException("Wrong new password");
        }
    }

    private void validateAuthorizationData(String login, String password) throws ServiceException {
        if (!UserValidator.validateLogin(login)) {
            throw new ServiceException("Wrong email");
        }
        if (!UserValidator.validatePassword(password)) {
            throw new ServiceException("Wrong password");
        }
    }

    private void validateEditUserData(User user) throws ServiceException {
        if (!UserValidator.validateName(user.getName())) {
            throw new ServiceException("Wrong name");
        }
        if (!UserValidator.validatePhone(user.getPhoneNumber())) {
            throw new ServiceException("Wrong phone number");
        }
        if (UserValidator.validateCity(user.getCity().getId())) {
            throw new ServiceException("Wrong cityID");
        }
    }

    private void validateUserData(User user) throws ServiceException {
        if (!UserValidator.validateLogin(user.getLogin())) {
            throw new ServiceException("Wrong email");
        }
        if (!UserValidator.validatePassword(user.getPassword())) {
            throw new ServiceException("Wrong password");
        }
        if (!UserValidator.validateName(user.getName())) {
            throw new ServiceException("Wrong name");
        }
        if (!UserValidator.validatePhone(user.getPhoneNumber())) {
            throw new ServiceException("Wrong phone number");
        }
        if (UserValidator.validateCity(user.getCity().getId())) {
            throw new ServiceException("Wrong cityID");
        }
    }
}
