package service;

import com.example.bd_project.dao.BuyLicensDao;
import com.example.bd_project.dao.CarDao;
import com.example.bd_project.entity.BuyLicens;
import com.example.bd_project.entity.Car;
import com.example.bd_project.util.DateParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class CarService implements Service {
    private final CarDao dao;

    public CarService() {
        dao = new CarDao();
    }

    @Override
    public void addRecord() throws IOException, ParseException {
        Car car = new Car();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter license_plate(string):");
        car.setLicense_plate(scanner.next());
        System.out.println("Enter model(string):");
        car.setModel(scanner.next());
        System.out.println("Enter type(string):");
        car.setType(scanner.next());
        System.out.println("Enter color(string):");
        car.setColor(scanner.next());
        System.out.println("Enter engine_number(long):");
        car.setEngine_number(scanner.nextLong());
        System.out.println("Enter car_body_number(long):");
        car.setCar_body_number(scanner.nextLong());
        System.out.println("Enter chassis_number(long):");
        car.setChassis_number(scanner.nextLong());
        System.out.println("Enter release_date(date format: yyyy/MM/dd):");
        car.setRelease_date(DateParser.readDate());
        System.out.println("Enter car_mileage(int):");
        car.setCar_mileage(scanner.nextInt());
        System.out.println("Enter release_price(int):");
        car.setRelease_price(scanner.nextInt());

        System.out.println("Write sale_price?");
        boolean check = scanner.nextBoolean();
        if (check) {
            System.out.println("Enter sale_price(int):");
            car.setSale_price(scanner.nextInt());
        }
        System.out.println("Write buy_price?");
        check = scanner.nextBoolean();
        if (check) {
            System.out.println("Enter buy_price(int):");
            car.setBuy_price(scanner.nextInt());
        }
        System.out.println("Write number_tech_cond?");
        check = scanner.nextBoolean();
        if (check) {
            System.out.println("Enter number_tech_cond(long):");
            car.setNumber_tech_cond(scanner.nextLong());
        }
        System.out.println("Write date_tech_cond?");
        check = scanner.nextBoolean();
        if (check) {
            System.out.println("Enter date_tech_cond(date format: yyyy/MM/dd):");
            car.setDate_tech_cond(DateParser.readDate());
        }
        System.out.println("Write expert_full_name?");
        check = scanner.nextBoolean();
        if (check) {
            System.out.println("Enter expert_full_name(string):");
            car.setExpert_full_name(scanner.next());
        }

        dao.add(car);
    }

    @Override
    public void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter license_plate(string):");
        dao.delete(scanner.next());
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("license_plate = " + entry.getLicense_plate());
            System.out.println("model = " + entry.getModel());
            System.out.println("type = " + entry.getType());
            System.out.println("color = " + entry.getColor());
            System.out.println("engine_number = " + entry.getEngine_number());
            System.out.println("car_body_number = " + entry.getCar_body_number());
            System.out.println("chassis_number = " + entry.getChassis_number());
            System.out.println("release_date = " + DateParser.getString(entry.getRelease_date()));
            System.out.println("car_mileage = " + entry.getCar_mileage());
            System.out.println("release_price = " + entry.getRelease_price());
            System.out.println("sale_price = " + entry.getSale_price());
            System.out.println("buy_price = " + entry.getBuy_price());
            System.out.println("number_tech_cond = " + entry.getNumber_tech_cond());
            System.out.println("date_tech_cond = " + DateParser.getString(entry.getDate_tech_cond()));
            System.out.println("expert_full_name = " + entry.getExpert_full_name());
        });

    }

    @Override
    public void changeRecord() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter key(long):");
        String key = scanner.next();
        Optional<Car> car = dao.show()
                .stream()
                .filter(entry -> Objects.equals(entry.getLicense_plate(), key))
                .findAny();
        if (car.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change license_plate = " + car.get().getLicense_plate() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            car.get().setLicense_plate(scanner.next());
        }
        System.out.println("Change model = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setModel(scanner.next());
        }
        System.out.println("Change type = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setType(scanner.next());
        }
        System.out.println("Change color = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setColor(scanner.next());
        }
        System.out.println("Change engine_number = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setEngine_number(scanner.nextLong());
        }
        System.out.println("Change car_body_number = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setCar_body_number(scanner.nextLong());
        }
        System.out.println("Change chassis_number = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setChassis_number(scanner.nextLong());
        }
        System.out.println("Change release_date = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setRelease_date(DateParser.readDate());
        }
        System.out.println("Change car_mileage = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setCar_mileage(scanner.nextInt());
        }
        System.out.println("Change release_price = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setRelease_price(scanner.nextInt());
        }
        System.out.println("Change sale_price = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setSale_price(scanner.nextInt());
        }
        System.out.println("Change buy_price = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setBuy_price(scanner.nextInt());
        }
        System.out.println("Change number_tech_cond = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setNumber_tech_cond(scanner.nextLong());
        }
        System.out.println("Change date_tech_cond = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setDate_tech_cond(DateParser.readDate());
        }
        System.out.println("Change expert_full_name = " + car.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            car.get().setExpert_full_name(scanner.next());
        }
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}
