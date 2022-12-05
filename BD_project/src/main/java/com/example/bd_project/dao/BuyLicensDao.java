package com.example.bd_project.dao;

import com.example.bd_project.entity.Sellers;
import com.example.bd_project.entity.Spare;
import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.BuyLicens;
import org.hibernate.Session;

import java.util.List;

public class BuyLicensDao implements Dao<BuyLicens, Long> {
    private final Session session;

    public BuyLicensDao(Session session) {
        this.session = session;
    }

    @Override
    public List<BuyLicens> show() {
        return  session.createQuery("SELECT p FROM BuyLicens p", BuyLicens.class).list();
    }

    @Override
    public void add(BuyLicens buyLicens) {
        session.getTransaction().begin();
        session.save(buyLicens);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM BuyLicens WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(BuyLicens buyLicens, Long key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE BuyLicens" +
                " SET " +
                "id_buy_license =: id_buy_license, " +
                "name =: name, " +
                "issue_date=: issue_date," +
                "employee_full_name =: employee_full_name " +
                "WHERE id_buy_license =: i")
                .setParameter("id_buy_license", buyLicens.getId_buy_license())
                .setParameter("name", buyLicens.getName())
                .setParameter("issue_date", buyLicens.getIssue_date())
                .setParameter("employee_full_name", buyLicens.getEmployee_full_name())
                .setParameter("i", buyLicens.getId_buy_license())
                .executeUpdate();
        session.getTransaction().commit();
    }

    public void close() {}

    @Override
    public List<BuyLicens> search(String param) {
        String str = String.join(" AND ", param.split(" "));
        System.out.println("SELECT p FROM PaymentType p WHERE " + str);
        return session.createQuery("SELECT p FROM BuyLicens p WHERE " + str, BuyLicens.class)
                .list();
    }
}
