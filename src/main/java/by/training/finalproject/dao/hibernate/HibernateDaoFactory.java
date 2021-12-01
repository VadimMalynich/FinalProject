package by.training.finalproject.dao.hibernate;


import by.training.finalproject.dao.hibernate.impl.*;

public class HibernateDaoFactory {
    private static final HibernateDaoFactory instance = new HibernateDaoFactory();

    private HibernateDaoFactory() {
    }

    private final HibernateAdDao hibernateAdDao = new HibernateAdDaoImpl();
    private final HibernateCityDao hibernateCityDao = new HibernateCityDaoImpl();
    private final HibernateClothesTypeDao hibernateClothesTypeDao = new HibernateClothesTypeDaoImpl();
    private final HibernateCommentDao hibernateCommentDao = new HibernateCommentDaoImpl();
    private final HibernateMessengersDao hibernateMessengersDao = new HibernateMessengersDaoImpl();
    private final HibernateUserDao hibernateUserDao = new HibernateUserDaoImpl();

    public static HibernateDaoFactory getInstance() {
        return instance;
    }

    public HibernateAdDao getAdDao() {
        return hibernateAdDao;
    }

    public HibernateCityDao getCityDao() {
        return hibernateCityDao;
    }

    public HibernateClothesTypeDao getClothesTypeDao() {
        return hibernateClothesTypeDao;
    }

    public HibernateCommentDao getCommentDao() {
        return hibernateCommentDao;
    }

    public HibernateMessengersDao getMessengersDao() {
        return hibernateMessengersDao;
    }

    public HibernateUserDao getUserDao() {
        return hibernateUserDao;
    }
}
