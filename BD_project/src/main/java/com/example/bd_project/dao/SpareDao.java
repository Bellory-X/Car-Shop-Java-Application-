package com.example.bd_project.dao;

import com.example.bd_project.entity.SpareKey;
import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.Spare;
import org.hibernate.Session;

import java.util.List;

public class SpareDao implements Dao<Spare, SpareKey> {
    private final Session session;

    public SpareDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Spare> show() {
        return  session.createQuery("SELECT p FROM Spare p", Spare.class).list();
    }

    @Override
    public void add(Spare spare) {
        session.getTransaction().begin();
        session.save(spare);
        session.getTransaction().commit();
    }

    @Override
    public void delete(SpareKey key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM Spare " +
                "WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(Spare spare, SpareKey key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE Spare " +
                "SET " +
                "key =: key, " +
                "stash_count =: stash_count " +
                "WHERE id =: i")
                .setParameter("key", spare.getKey())
                .setParameter("stash_count", spare.getStash_count())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
