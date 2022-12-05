package com.example.bd_project.dao;

import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.StatusChange;
import org.hibernate.Session;

import java.util.List;

public class StatusChangeDao implements Dao<StatusChange, Integer> {
    private final Session session;

    public StatusChangeDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<StatusChange> show() {
        return  session.createQuery("SELECT p FROM StatusChange p", StatusChange.class)
                .list();
    }

    @Override
    public void add(StatusChange statusChange) {
        session.getTransaction().begin();
        session.save(statusChange);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Integer key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM StatusChange " +
                "WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(StatusChange statusChange, Integer key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE StatusChange " +
                "SET " +
                "number =: number, " +
                "cause =: cause, " +
                "position =: position, " +
                "order_date =: order_date " +
                "WHERE id =: i")
                .setParameter("number", statusChange.getNumber())
                .setParameter("cause", statusChange.getCause())
                .setParameter("position", statusChange.getPosition())
                .setParameter("order_date", statusChange.getOrder_date())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
