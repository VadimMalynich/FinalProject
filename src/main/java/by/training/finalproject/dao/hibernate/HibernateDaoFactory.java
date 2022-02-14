package by.training.finalproject.dao.hibernate;

public class HibernateDaoFactory {
    private static final HibernateDaoFactory instance = new HibernateDaoFactory();

    private HibernateDaoFactory() {
    }

    private final AdDao adDao = new AdDao();
    private final CityDao cityDao = new CityDao();
    private final ClothesTypeDao clothesTypeDao = new ClothesTypeDao();
    private final CommentDao commentDao = new CommentDao();
    private final UserDao userDao = new UserDao();

    public static HibernateDaoFactory getInstance() {
        return instance;
    }

    public AdDao getAdDao() {
        return adDao;
    }

    public CityDao getCityDao() {
        return cityDao;
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
