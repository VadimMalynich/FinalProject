package by.training.finalproject.service.impl;

import by.training.finalproject.bean.Comments;
import by.training.finalproject.bean.User;
import by.training.finalproject.dao.connection.ConnectionPool;
import by.training.finalproject.dao.exception.ConnectionPoolException;
import by.training.finalproject.service.ServiceException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.testng.Assert.*;

public class CommentServiceImplTest {
    private final CommentServiceImpl service = new CommentServiceImpl();
    private List<Comments> firstAdCommentsList = new ArrayList<>();
    private List<Comments> secondAdCommentsList = new ArrayList<>();
    private List<Comments> thirdAdCommentsList = new ArrayList<>();

    @BeforeTest
    public void init() throws ConnectionPoolException {
        ConnectionPool.getInstance().init();
        Comments comment = new Comments(6, 5, 3, "Коллекционное издание", Timestamp.valueOf("2021-08-26 15:01:38"));
        comment.getUser().setLogin("example@mail.ru");
        firstAdCommentsList.add(comment);
        comment = new Comments(2, 6, 1, "Эксклюзивный товар!", Timestamp.valueOf("2021-08-20 22:36:09"));
        comment.getUser().setLogin("admin@gmail.com");
        secondAdCommentsList.add(comment);
        comment = new Comments(3, 6, 4, "Покупал уже. Жена носит с удовольствием", Timestamp.valueOf("2021-08-22 10:08:30"));
        comment.getUser().setLogin("vadim.malynich@gmail.com");
        secondAdCommentsList.add(comment);
        comment = new Comments(5, 6, 2, "Можете посоветовать друзьям.", Timestamp.valueOf("2021-08-24 16:26:10"));
        comment.getUser().setLogin("slowman@yandex.ru");
        secondAdCommentsList.add(comment);
        comment = new Comments(1, 2, 2, "Эх не мой размер. Так бы взял.", Timestamp.valueOf("2021-08-20 18:05:49"));
        comment.getUser().setLogin("slowman@yandex.ru");
        thirdAdCommentsList.add(comment);
        comment = new Comments(4, 2, 1, "Возможно скоро появятся другие размеры следите за объявлениями", Timestamp.valueOf("2021-08-22 11:04:58"));
        comment.getUser().setLogin("admin@gmail.com");
        thirdAdCommentsList.add(comment);
    }

    @AfterTest
    public void destroy() throws ConnectionPoolException {
        ConnectionPool.getInstance().destroy();
    }

    @DataProvider(name = "testGetAllProvider")
    public Object[][] createDataForGetCommentsById() {
        return new Object[][]{
                {5, firstAdCommentsList},
                {6, secondAdCommentsList},
                {2, thirdAdCommentsList}
        };
    }

    @DataProvider(name = "testGetAllNegativeProvider")
    public Object[][] createDataForGetCommentsNegative() {
        return new Object[][]{
                {null, firstAdCommentsList},
                {0, firstAdCommentsList},
                {-5485, firstAdCommentsList},
                {-25, firstAdCommentsList}
        };
    }

    @DataProvider(name = "testDeleteNegativeProvider")
    public Object[][] createDataForDeleteNegative() {
        return new Object[][]{
                {null},
                {0},
                {-5485},
                {-25}
        };
    }

    @DataProvider(name = "testAddNegativeProvider")
    public Object[][] createDataForAddNegative() {
        return new Object[][]{
                {null},
                {new Comments(-5, -5, "dsadwqdsadsa", new Timestamp(Calendar.getInstance().getTimeInMillis()))},
                {new Comments(-5, 3, "dsadwqdsadsa", new Timestamp(Calendar.getInstance().getTimeInMillis()))},
                {new Comments(1, 4, "ргалуц@#$%^&REWDSS ew wuhaksdjn sqd jksqh dlsjk dwlанпрfwejk hjwd qwd54 qwd848 5asd498 w9ef6d4s4f8ds4f5s4a4dsaaaaaaaaaaaaaad48wds5a4d5as4d64 as864d98as4d6as45d4as5dx489w8f9ergtr7h64tyjy7u9j8h6fdf1swf45we64fsd798f45sd34f98w65dsf498ef6w", new Timestamp(Calendar.getInstance().getTimeInMillis()))}
        };
    }

    @Test(testName = "Positive scenario of getting ad comments", dataProvider = "testGetAllProvider")
    public void testGetAll(Integer id, List<Comments> result) throws ServiceException {
        List<Comments> actual = service.getAll(id);
        assertEquals(actual, result);
    }

    @Test(testName = "Negative scenario of getting ad comments", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong adId for get comments", dataProvider = "testGetAllNegativeProvider")
    public void testGetAllNegative(Integer id, List<Comments> result) throws ServiceException {
        List<Comments> actual = service.getAll(id);
        assertEquals(actual, result);
    }

    @Test(testName = "Negative scenario of adding comment", expectedExceptions = ServiceException.class,
            dataProvider = "testAddNegativeProvider")
    public void testAddNegative(Comments comment) throws ServiceException {
        service.add(comment);
    }

    @Test(testName = "Negative scenario of deleting comment", expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Wrong id for delete comment", dataProvider = "testDeleteNegativeProvider")
    public void testDeleteNegative(Integer id) throws ServiceException {
        service.delete(id);
    }
}