package com.example.bd_project.dao;

import com.example.bd_project.entity.EmployeesKey;
import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.Employees;
import org.hibernate.Session;

import java.util.List;

public class EmployeesDao implements Dao<Employees, EmployeesKey> {
    private final Session session;

    public EmployeesDao() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Employees> show() {
        return  session.createQuery("SELECT p FROM Employees p", Employees.class).list();
    }

    @Override
    public void add(Employees employees) {
        session.getTransaction().begin();
        session.save(employees);
        session.getTransaction().commit();
    }

    @Override
    public void delete(EmployeesKey key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM Employees WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(Employees employees, EmployeesKey key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE Employees " +
                "SET " +
                "key =: key, " +
                "position =: position, " +
                "salary=: salary, " +
                "change_status =: change_status " +
                "WHERE id =: i")
                .setParameter("key", employees.getKey())
                .setParameter("position", employees.getPosition())
                .setParameter("salary", employees.getSalary())
                .setParameter("change_status", employees.getChange_status())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close() {
        session.close();
    }
}
