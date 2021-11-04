package by.training.finalproject.service.impl;

import by.training.finalproject.bean.*;
import by.training.finalproject.dao.connection.ConnectionPool;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.service.ServiceException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    private final UserServiceImpl service = new UserServiceImpl();
    private List<User> result = new ArrayList<>();
    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private User fourthUser;
    private User firstUserAdInfo;
    private User secondUserAdInfo;

    @BeforeTest
    public void init() throws ConnectionPoolException {
        ConnectionPool.getInstance().init();
        firstUser = new User(1, "admin@gmail.com", "$31$14$Fs3Fi-2rlGMW3eCO2cw7F68p4NKVPVs4Al6N_7gu150",
                "Алексей", "+375294822432", UserRole.ADMINISTRATOR, new City(16, Region.BREST, "Малорита"),
                new Messengers(true, true, true));
        secondUser = new User(2, "slowman@yandex.ru", "$31$14$1a944_v9szD_A4yzg6QMcE5NhV9-w3OeFu82k64knlU",
                "Vasiliy", "+375245520036", UserRole.USER, new City(95, Region.MOGILEV, "Горки"),
                new Messengers(false, false, false));
        thirdUser = new User(3, "example@mail.ru", "$31$14$kD5S6aQM-ETST189g2HVODDrEnxgcmbrq8s1RREZgD8",
                "Георгий", "+375332140036", UserRole.USER, new City(54, Region.GRODNO, "Лида"),
                new Messengers(false, true, false));
        fourthUser = new User(4, "vadim.malynich@gmail.com", "$31$14$yAHS2sIdUHYlJJV5zDixYywuniTQrfT5q_P4Yh5mhrw",
                "Vadim", "+375336695412", UserRole.USER, new City(54, Region.GRODNO, "Лида"),
                new Messengers(false, true, true));
        result.add(firstUser);
        result.add(secondUser);
        result.add(thirdUser);
        result.add(fourthUser);
        firstUserAdInfo = new User(2, "Vasiliy", "+375245520036", new City(95, Region.MOGILEV, "Горки"),
                new Messengers(false, false, false));
        secondUserAdInfo = new User(4, "Vadim", "+375336695412", new City(54, Region.GRODNO, "Лида"),
                new Messengers(false, true, true));
    }

    @AfterTest
    public void destroy() throws ConnectionPoolException {
        ConnectionPool.getInstance().destroy();
    }

    @DataProvider(name = "testAuthorizationProvider")
    public Object[][] createDataForAuthorizationUser() {
        return new Object[][]{
                {new String[]{"admin@gmail.com", "123456sS"}, firstUser},
                {new String[]{"example@mail.ru", "dwe54DSws"}, thirdUser},
                {new String[]{"vadim.malynich@gmail.com", "wdsad5wd5sWDsda652s"}, fourthUser},
                {new String[]{"slowman@yandex.ru", "Abcdef9"}, secondUser}
        };
    }

    @DataProvider(name = "testGetUserAdInfoProvider")
    public Object[][] createDataForGettingUserAdInfo() {
        return new Object[][]{
                {4, secondUserAdInfo},
                {2, firstUserAdInfo}
        };
    }

    @DataProvider(name = "testRegistrationNegativeProvider")
    public Object[][] createDataRegistrationNegative() {
        return new Object[][]{
                {new User(4, "example@gmail.com", "123456aQ", "Vadim", "+375295476300",
                        UserRole.USER, new City(54, Region.GRODNO, "Лида")), "5as4d8wD"},
                {new User(4, "example@gmail.com", "IDWDSd5", "Vadim", "+375295476300",
                        UserRole.USER, new City(54, Region.GRODNO, "Лида")), "lDWDSd5"},
                {new User(4, "example@gmail.com", "iiiIII40", "Vadim", "+375295476300",
                        UserRole.USER, new City(54, Region.GRODNO, "Лида")), "iiiIII4O"},
                {new User(4, "example@gmail.com", "DODOD5d", "Vadim", "+375295476300",
                        UserRole.USER, new City(54, Region.GRODNO, "Лида")), "D0D0D5d"},
                {null, "5as4d8wD"}
        };
    }

    @DataProvider(name = "testIdProvider")
    public Object[][] createDataForNegativeId() {
        return new Object[][]{
                {null},
                {-5},
                {0},
                {-1586}
        };
    }

    @Test
    public void testGetAllUser() throws ServiceException {
        List<User> actual = service.getAllUser();
        assertEquals(actual, result);
    }

    @Test(testName = "Positive scenario of authorisation", dataProvider = "testAuthorizationProvider")
    public void testAuthorization(String[] data, User result) throws ServiceException {
        User actual = service.authorization(data[0], data[1]);
        assertEquals(actual, result);
    }

    @Test(testName = "Positive scenario of getting needed user info for ad", dataProvider = "testGetUserAdInfoProvider")
    public void testGetUserAdInfo(Integer id, User result) throws ServiceException {
        User actual = service.getUserAdInfo(id);
        assertEquals(actual, result);
    }

    @Test(testName = "Negative scenario of getting needed user info for ad",expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Invalid ad id for take user info", dataProvider = "testIdProvider")
    public void testGetUserAdInfo(Integer id) throws ServiceException {
        service.getUserAdInfo(id);
    }

    @Test(testName = "Positive scenario of getting needed user info for ad", expectedExceptions = ServiceException.class,
            dataProvider = "testRegistrationNegativeProvider")
    public void testRegistrationNegative(User user, String confirmPassword) throws ServiceException {
        service.registration(user, confirmPassword, null);
    }

    @Test(testName = "Positive scenario of getting needed user info for ad", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong id for delete user", dataProvider = "testIdProvider")
    public void testDeleteUserNegative(Integer id) throws ServiceException {
        service.delete(id);
    }


}