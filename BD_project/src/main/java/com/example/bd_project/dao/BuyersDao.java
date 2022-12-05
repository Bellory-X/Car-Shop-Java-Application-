package com.example.bd_project.dao;

import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.Buyers;
import org.hibernate.Session;

import java.util.List;

public class BuyersDao implements Dao<Buyers, Long> {
    private final Session session;

    public BuyersDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Buyers> show() {
        return  session.createQuery("SELECT p FROM Buyers p", Buyers.class).list();
    }

    @Override
    public void add(Buyers buyers) {
        session.getTransaction().begin();
        session.save(buyers);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM Buyers WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(Buyers newBuyers, Long key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE Buyers" +
                " SET " +
                "passport_number =: passport_number," +
                "license_plate =: license, " +
                "sale_date =: sale, " +
                "order_number=: order," +
                "payment_type =: payment " +
                "WHERE id =: i")
                .setParameter("passport_number", newBuyers.getPassport_number())
                .setParameter("license", newBuyers.getLicense_plate())
                .setParameter("sale", newBuyers.getSale_date())
                .setParameter("order", newBuyers.getOrder_number())
                .setParameter("payment", newBuyers.getPayment_type())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
