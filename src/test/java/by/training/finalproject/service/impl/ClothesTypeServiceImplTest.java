package by.training.finalproject.service.impl;

import by.training.finalproject.bean.ClothesType;
import by.training.finalproject.dao.connection.ConnectionPool;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.service.ServiceException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ClothesTypeServiceImplTest {
    private final ClothesTypeServiceImpl service = new ClothesTypeServiceImpl();
    private List<ClothesType> result = new ArrayList<>();

    @BeforeTest
    public void init() throws ConnectionPoolException {
        ConnectionPool.getInstance().init();
        ClothesType category1 = new ClothesType(1, "Другой", 3);
        ClothesType category2 = new ClothesType(2, "Брюки", 1);
        ClothesType category3 = new ClothesType(3, "Рубашка", 1);
        ClothesType category4 = new ClothesType(4, "Майка", 1);
        result.add(category1);
        result.add(category2);
        result.add(category3);
        result.add(category4);
    }

    @AfterTest
    public void destroy() throws ConnectionPoolException {
        ConnectionPool.getInstance().destroy();
    }

    @DataProvider(name = "testGetClothesCategoryPositiveProvider")
    public Object[][] createDataForTestCategoryPositive() {
        return new Object[][]{
                {4, "Майка"},
                {2, "Брюки"},
                {3, "Рубашка"},
                {1, "Другой"},
                {5, null}
        };
    }

    @DataProvider(name = "testGetClothesCategoryNegativeDataProvider")
    public Object[][] createDataForTestCategoryNegativeData() {
        return new Object[][]{
                {0, "Майка"},
                {-6, "Брюки"},
                {null, "Рубашка"},
                {-100, "Другой"}
        };
    }

    @DataProvider(name = "testDeleteClothesCategoryNegativeProvider")
    public Object[][] createDataForTestDeleteCategoryNegative() {
        return new Object[][]{
                {1},
                {3},
                {4},
                {2}
        };
    }

    @DataProvider(name = "testAddEditClothesCategoryNegativeProvider")
    public Object[][] createDataForTestAddEditCategoryNegative() {
        return new Object[][]{
                {new ClothesType()},
                {new ClothesType("")}
        };
    }

    @Test(testName = "Positive scenario of getting all records from database")
    public void testGetAll() throws ServiceException {
        List<ClothesType> actual = service.getAll();
        assertEquals(actual, result);
    }

    @Test(testName = "Positive scenario of getting clothes category", dataProvider = "testGetClothesCategoryPositiveProvider")
    public void testGetClothesCategoryPositive(Integer id, String result) throws ServiceException {
        String actual = service.getClothesCategory(id);
        assertEquals(actual, result);
    }

    @Test(testName = "Negative scenario of getting clothes category", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Impossible to get category name!",
            dataProvider = "testGetClothesCategoryNegativeDataProvider")
    public void testGetClothesCategoryNegativeData(Integer id, String result) throws ServiceException {
        String actual = service.getClothesCategory(id);
        assertEquals(actual, result);
    }

    @Test(testName = "Negative scenario of deleting clothes category", expectedExceptions = ServiceException.class,
            dataProvider = "testDeleteClothesCategoryNegativeProvider")
    public void testDeleteClothesCategoryNegative(Integer id) throws ServiceException {
        service.delete(id);
    }

    @Test(testName = "Negative scenario of adding clothes category", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong category name", dataProvider = "testAddEditClothesCategoryNegativeProvider")
    public void testAddClothesCategoryNegative(ClothesType type) throws ServiceException {
        service.add(type);
    }

    @Test(testName = "Negative scenario of editing clothes category", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong category name", dataProvider = "testAddEditClothesCategoryNegativeProvider")
    public void testEditClothesCategoryNegative(ClothesType type) throws ServiceException {
        service.edit(type);
    }

}