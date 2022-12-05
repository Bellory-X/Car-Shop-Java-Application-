package com.example.bd_project.dao;

import com.example.bd_project.entity.PaymentType;
import com.example.bd_project.entity.Spare;
import org.hibernate.*;


import java.util.List;

public class PaymentTypeDao implements Dao<PaymentType, Integer> {
    private final Session session;

    public PaymentTypeDao(Session session) {
        this.session = session;
    }

    @Override
    public List<PaymentType> show() {
        return session.createQuery("SELECT p FROM PaymentType p", PaymentType.class)
                .list();
    }

    @Override
    public void add(PaymentType paymentType) {
        session.getTransaction().begin();
        session.save(paymentType);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Integer key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM PaymentType WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(PaymentType paymentType, Integer key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE PaymentType " +
                "SET " +
                "payment_id =: payment_id, " +
                "payment_name =: name " +
                "WHERE id =: i")
                .setParameter("payment_id", paymentType.getPayment_id())
                .setParameter("name", paymentType.getPayment_name())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close(){}

    @Override
    public List<PaymentType> search(String param) {
        String str = String.join(" AND ", param.split(" "));
        System.out.println("SELECT p FROM PaymentType p WHERE " + str);
        return session.createQuery("SELECT p FROM PaymentType p WHERE " + str, PaymentType.class)
                .list();
    }
}
