package by.training.finalproject.service.impl;

import by.training.finalproject.bean.*;
import by.training.finalproject.dao.connection.ConnectionPool;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.service.ServiceException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class AdInfoServiceImplTest {
    private final AdInfoServiceImpl service = new AdInfoServiceImpl();
    private List<AdInfo> result = new ArrayList<>();
    private AdInfo firstAdFullInfo;
    private AdInfo secondAdFullInfo;

    @BeforeTest
    public void init() throws ConnectionPoolException {
        ConnectionPool.getInstance().init();
        AdInfo adInfo = new AdInfo(1, 3, 3, 0, 1,
                new Ad(Date.valueOf("2021-07-23"), "Приталеная рубашка", "Рубашка новая. Продаю так как не подошла по размеру.", "/resources/images/adsPhoto/default.png"));
        result.add(adInfo);
        adInfo = new AdInfo(2, 3, 1, 2, 3,
                new Ad(Date.valueOf("2021-08-09"), "Свитер бабушкин", "Бабушка мне связала и вам свяжет", "/resources/images/adsPhoto/default.png"));
        result.add(adInfo);
        adInfo = new AdInfo(3, 4, 1, 0, 0,
                new Ad(Date.valueOf("2021-05-16"), "Худи clown", "Лиметированное худи может оказаться у вас.", "/resources/images/adsPhoto/default.png"));
        result.add(adInfo);
        adInfo = new AdInfo(4, 2, 4, 0, 0,
                new Ad(Date.valueOf("2021-06-29"), "Майка Polo", "Майка новая. Продаю так как не подошла по размеру.", "/resources/images/adsPhoto/default.png"));
        result.add(adInfo);
        adInfo = new AdInfo(5, 3, 1, 1, 2,
                new Ad(Date.valueOf("2021-08-26"), "Панамка", "Черная панама хорошо защищает от солнца зимой и летом", "/resources/images/adsPhoto/default.png"));
        result.add(adInfo);
        adInfo = new AdInfo(6, 2, 2, 3, 0,
                new Ad(Date.valueOf("2021-08-15"), "Чёрные брюки", "Молодежные стильные брюки.", "/resources/images/adsPhoto/default.png"));
        result.add(adInfo);


        User userAdInfo = new User(4, "Vadim", "+375336695412", new City(54, Region.GRODNO, "Лида"),
                new Messengers(false, true, true));
        Ad ad = new Ad(Date.valueOf("2021-05-16"), "Худи clown", "Лен", ClothesSize.XXL, ClothesSex.UNISEX,
                "Лиметированное худи может оказаться у вас.", "/resources/images/adsPhoto/default.png");
        firstAdFullInfo = new AdInfo(3, userAdInfo, new ClothesType(1, "Другой"), ad, 0, 0);


        userAdInfo = new User(2, "Vasiliy", "+375245520036", new City(95, Region.MOGILEV, "Горки"),
                new Messengers(false, false, false));
        ad = new Ad(Date.valueOf("2021-06-29"), "Майка Polo", "Хлопок", ClothesSize.XL, ClothesSex.MAN,
                "Майка новая. Продаю так как не подошла по размеру.", "/resources/images/adsPhoto/default.png");
        secondAdFullInfo = new AdInfo(4, userAdInfo, new ClothesType(4, "Майка"), ad, 0, 0);
    }

    @AfterTest
    public void destroy() throws ConnectionPoolException {
        ConnectionPool.getInstance().destroy();
    }

    @DataProvider(name = "testGetAdInfoProvider")
    public Object[][] createDataForGetAdInfo() {
        return new Object[][]{
                {4, secondAdFullInfo},
                {3, firstAdFullInfo}
        };
    }

    @DataProvider(name = "testLikePressedProvider")
    public Object[][] createDataForCheckPressedLike() {
        return new Object[][]{
                {5, 2},
                {5, 3},
                {1, 4},
                {2, 2},
                {2, 1}
        };
    }

    @DataProvider(name = "testLikeNotPressedProvider")
    public Object[][] createDataForCheckNotPressedLike() {
        return new Object[][]{
                {3, 1},
                {4, 4},
                {1, 3},
                {2, 3},
                {5, 1}
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

    @DataProvider(name = "testIdsProvider")
    public Object[][] createDataForNegativeIds() {
        return new Object[][]{
                {null, null},
                {null, 5},
                {5, null},
                {-5, 1},
                {-5, -3},
                {3, -9},
                {0, 4},
                {4, 0},
        };
    }

    @Test(testName = "Positive scenario of getting all ads info")
    public void testGetAll() throws ServiceException {
        List<AdInfo> actual = service.getAll();
        assertEquals(actual, result);
    }

    @Test(testName = "Positive scenario of getting full ad info", dataProvider = "testGetAdInfoProvider")
    public void testGetAdInfo(Integer id, AdInfo result) throws ServiceException {
        AdInfo actual = service.getAdInfo(id);
        assertEquals(actual, result);
    }

    @Test(testName = "Scenario when the user put a like", dataProvider = "testLikePressedProvider")
    public void testLikePressed(Integer adId, Integer userId) throws ServiceException {
        assertTrue(service.isLikePressed(adId, userId));
    }

    @Test(testName = "Scenario when the user don't put a like", dataProvider = "testLikeNotPressedProvider")
    public void testLikeNotPressed(Integer adId, Integer userId) throws ServiceException {
        assertFalse(service.isLikePressed(adId, userId));
    }

    @Test(testName = "Negative scenario of deleting ad info", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong id for delete ad", dataProvider = "testIdProvider")
    public void testDeleteAdNegative(Integer id) throws ServiceException {
        service.delete(id);
    }

    @Test(testName = "Negative scenario of getting ad info", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong id for load ad info", dataProvider = "testIdProvider")
    public void testGetAdInfoNegative(Integer id) throws ServiceException {
        service.getAdInfo(id);
    }

    @Test(testName = "Negative scenario of changing like", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong user or ad id", dataProvider = "testIdsProvider")
    public void testChangeLikesNegative(Integer firstId, Integer secondId) throws ServiceException {
        service.changeLikesCounter(firstId, secondId);
    }

    @Test(testName = "Negative scenario of checking whether the like is pressed", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong user or ad id", dataProvider = "testIdsProvider")
    public void testIsLikePressedNegative(Integer firstId, Integer secondId) throws ServiceException {
        service.isLikePressed(firstId, secondId);
    }
}