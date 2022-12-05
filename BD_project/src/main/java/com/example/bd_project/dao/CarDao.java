package com.example.bd_project.dao;

import com.example.bd_project.util.HibernateUtil;
import com.example.bd_project.entity.Car;
import org.hibernate.Session;

import java.util.List;

public class CarDao implements Dao<Car, String> {
    private final Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    @Override
    public List<Car> show() {
        return  session.createQuery("SELECT p FROM Car p", Car.class).list();
    }

    @Override
    public void add(Car car) {
        session.getTransaction().begin();
        session.save(car);
        session.getTransaction().commit();
    }

    @Override
    public void delete(String key) {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM Car WHERE id =: i")
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void change(Car car, String key) {
        session.getTransaction().begin();
        session.createQuery("UPDATE Car " +
                "SET " +
                "license_plate =: license_plate, " +
                "model =: model, " +
                "type=: type, " +
                "color =: color, " +
                "engine_number =: engine_number, " +
                "car_body_number =: car_body_number, " +
                "chassis_number =: chassis_number, " +
                "release_date =: release_date, " +
                "car_mileage =: car_mileage, " +
                "release_price=: release_price, " +
                "sale_price =: sale_price, " +
                "buy_price =: buy_price , " +
                "number_tech_cond =: number_tech_cond, " +
                "date_tech_cond = : date_tech_cond, " +
                "expert_full_name =: expert_full_name " +
                "WHERE license_plate =: i")
                .setParameter("license_plate", car.getLicense_plate())
                .setParameter("model", car.getModel())
                .setParameter("type", car.getType())
                .setParameter("color", car.getColor())
                .setParameter("engine_number", car.getEngine_number())
                .setParameter("car_body_number", car.getCar_body_number())
                .setParameter("chassis_number", car.getChassis_number())
                .setParameter("release_date", car.getRelease_date())
                .setParameter("car_mileage", car.getCar_mileage())
                .setParameter("release_price", car.getRelease_price())
                .setParameter("sale_price", car.getSale_price())
                .setParameter("buy_price", car.getBuy_price())
                .setParameter("number_tech_cond", car.getNumber_tech_cond())
                .setParameter("date_tech_cond", car.getDate_tech_cond())
                .setParameter("expert_full_name", car.getExpert_full_name())
                .setParameter("i", key)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void close() {

    }
}
