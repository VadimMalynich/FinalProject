package by.training.finalproject.dao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final AdDao adInfoDao = new AdDao();
    private final ClothesTypeDao clothesTypeDao = new ClothesTypeDao();
    private final CommentDao commentDao = new CommentDao();
    private final UserDao userDao = new UserDao();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public AdDao getAdInfoDao() {
        return adInfoDao;
    }

    public ClothesTypeDao getClothesTypeDao() {
        return clothesTypeDao;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
