package by.training.finalproject.service.impl;

import by.training.finalproject.bean.City;
import by.training.finalproject.bean.Messengers;
import by.training.finalproject.bean.User;
import by.training.finalproject.dao.DaoFactory;
import by.training.finalproject.dao.UserDao;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.transaction.Transaction;
import by.training.finalproject.dao.transaction.TransactionFactory;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.UserService;
import by.training.finalproject.service.validation.PBKDF2Hasher;
import by.training.finalproject.service.validation.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static PBKDF2Hasher hasher = new PBKDF2Hasher(14);

    @Override
    public List<User> getAllUser() throws ServiceException {
        List<User> list;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                list = userDao.getAll();
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
        try {
            validateUserData(user);
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                char[] pass = user.getPassword().toCharArray();
                user.setPassword(hasher.hash(pass));
                userDao.add(user);
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
            throw new ServiceException("Wrong id for delete user");
        }
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                if (userDao.getUserRole(id) == 0) {
                    throw new ServiceException("Cannot delete admin");
                }
                userDao.delete(id);
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
    public void edit(User user, String[] messengers) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Can not edit user");
        }
        try {
            validateEditUserData(user);
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                user.setMessengers(checkMessengers(messengers));
                userDao.edit(user);
                userDao.editMessengers(user.getId(), user.getMessengers());
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
    public void edit(User user, String[] messengers, String oldPass, String newPass) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Can not edit user");
        }
        try {
            validateEditUserData(user);
            validatePasswords(oldPass, newPass);
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                user.setMessengers(checkMessengers(messengers));
                char[] pass = oldPass.toCharArray();
                if (hasher.checkPassword(pass, user.getPassword())) {
                    char[] newPassword = newPass.toCharArray();
                    user.setPassword(hasher.hash(newPassword));
                } else {
                    throw new ServiceException("Entered wrong old password");
                }
                userDao.edit(user);
                userDao.editMessengers(user.getId(), user.getMessengers());
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
    public User authorization(String login, String password) throws ServiceException {
        validateAuthorizationData(login, password);
        User user;
        try {
            DaoFactory factory = DaoFactory.getInstance();
            UserDao userDao = factory.getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                String dbPass = userDao.getUserPassword(login);
                if (dbPass == null) {
                    throw new ServiceException("Entered wrong login");
                }
                char[] pass = password.toCharArray();
                if (hasher.checkPassword(pass, dbPass)) {
                    user = userDao.authorization(login);
                } else {
                    throw new ServiceException("Entered wrong password");
                }
                transaction.commit();
            } catch (DaoException | ConnectionPoolException | IllegalArgumentException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }

        return user;
    }

    @Override
    public User getUser(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Invalid ad id for take user info");
        }
        User user;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                user = userDao.getUserInfo(id);
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
        return user;
    }

    @Override
    public User getUserAdInfo(Integer id) throws ServiceException {
        if (id == null || id < 1) {
            throw new ServiceException("Invalid ad id for take user info");
        }
        User user;
        try {
            DaoFactory factory = DaoFactory.getInstance();
            UserDao userDao = factory.getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                user = userDao.getUserAdInfo(id);
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
        return user;
    }

    @Override
    public List<City> getAllCities() throws ServiceException {
        List<City> list;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();
            Transaction transaction = TransactionFactory.getInstance().getEntityTransaction();
            try {
                transaction.initTransaction(userDao);
                list = userDao.getAllCities();
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
