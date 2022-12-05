package com.example.bd_project.dao;

import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.Sellers;
import org.hibernate.Session;

import java.util.List;

public class SellersDao implements Dao<Sellers, Integer> {
    private final Session session;

    public SellersDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Sellers> show() {
        return  session.createQuery("SELECT p FROM Sellers p", Sellers.class).list();
    }

    @Override
    public void add(Sellers sellers) {
        session.getTransaction().begin();
        session.save(sellers);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Integer key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM Sellers " +
                "WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(Sellers sellers, Integer key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE Sellers " +
                "SET " +
                "passport_number =: passport_number, " +
                "license_plate =: license_plate, " +
                "buy_date =: buy_date, " +
                "id_buy_license =: id_buy_license " +
                "WHERE id =: i")
                .setParameter("passport_number", sellers.getPassport_number())
                .setParameter("license_plate", sellers.getLicense_plate())
                .setParameter("buy_date", sellers.getBuy_date())
                .setParameter("id_buy_license", sellers.getId_buy_license())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
