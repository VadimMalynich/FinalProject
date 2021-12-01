package by.training.finalproject.dao.hibernate.impl;

import by.training.finalproject.bean.Messengers;
import by.training.finalproject.dao.exception.DaoException;
import by.training.finalproject.dao.hibernate.HibernateMessengersDao;
import org.hibernate.Session;

public class HibernateMessengersDaoImpl implements HibernateMessengersDao {
    @Override
    public Messengers getUserMessengers(Integer id, Session session) throws DaoException {
        try{
            return session.find(Messengers.class, id);
        } catch (Exception ex){
            throw new DaoException(ex);
        }
    }

    @Override
    public void add(Messengers messengers, Session session) throws DaoException {
        try{
            session.save(messengers);
        } catch (Exception ex){
            throw new DaoException(ex);
        }
    }

    @Override
    public void edit(Messengers messengers, Session session) throws DaoException {
        try{
            session.update(messengers);
        } catch (Exception ex){
            throw new DaoException(ex);
        }
    }
}
