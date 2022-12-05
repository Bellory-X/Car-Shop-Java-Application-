package com.example.bd_project.service;

import com.example.bd_project.dao.BuyersDao;
import com.example.bd_project.entity.Buyers;
import com.example.bd_project.util.DateParser;
import org.hibernate.Session;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class BuyersService implements Service {
    private final BuyersDao dao;

    public BuyersService(Session session) {
        dao = new BuyersDao(session);
    }

    @Override
    public void addRecord() throws IOException, ParseException {
        Buyers buyers = new Buyers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter passport_number(long):");
        buyers.setPassport_number(scanner.nextLong());
        System.out.println("Enter license_plate(string):");
        buyers.setLicense_plate(scanner.next());
        System.out.println("Enter sale_date(date format: yyyy/MM/dd):");
        buyers.setSale_date(DateParser.readDate());
        System.out.println("Enter order_number(long):");
        buyers.setOrder_number(scanner.nextLong());
        System.out.println("Enter payment_type(int):");
        buyers.setPayment_type(scanner.nextInt());

        dao.add(buyers);
    }

    @Override
    public void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter passport_number(long):");
        dao.delete(scanner.nextLong());
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("passport_number = " + entry.getPassport_number());
            System.out.println("license_plate = " + entry.getPassport_number());
            System.out.println("sale_date = " + DateParser.getString(entry.getSale_date()));
            System.out.println("order_number = " + entry.getOrder_number());
            System.out.println("payment_type = " + entry.getPayment_type());
        });
    }

    @Override
    public void changeRecord() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter key(long):");
        long key = scanner.nextLong();
        Optional<Buyers> buyers = dao.show()
                .stream()
                .filter(entry -> entry.getPassport_number() == key)
                .findAny();
        if (buyers.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change passport_number = " + buyers.get().getPassport_number() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            buyers.get().setPassport_number(scanner.nextLong());
        }
        System.out.println("Change license_plate = " + buyers.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyers.get().setLicense_plate(scanner.next());
        }
        System.out.println("Change sale_date = " + DateParser.getString(buyers.get().getSale_date()) + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyers.get().setSale_date(DateParser.readDate());
        }
        System.out.println("Change order_number = " + buyers.get().getOrder_number() + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyers.get().setOrder_number(scanner.nextLong());
        }
        System.out.println("Change payment_type = " + buyers.get().getPayment_type() + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyers.get().setPayment_type(scanner.nextInt());
        }
        dao.change(buyers.get(), key);
    }

    @Override
    public void closeSession() {
        dao.close();
    }

    @Override
    public void searchRecords() {
        System.out.println("Enter number columns:");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        if (count == 0)
            return;
        if (count < 0 || count > 2) {
            System.out.println("Invalid number: max number 2");
            searchRecords();
            return;
        }
        StringJoiner str = new StringJoiner(" ");
        for (int i = 0; i < count; i++) {
            String param;
            System.out.println("Enter column title:");
            param = scanner.next();
            System.out.println("Enter value:");
            param += "='" + scanner.next() + "'";
            str.add(param);
        }
        dao.search(str.toString()).forEach(entry -> {
            System.out.println("passport_number = " + entry.getPassport_number());
            System.out.println("license_plate = " + entry.getPassport_number());
            System.out.println("sale_date = " + DateParser.getString(entry.getSale_date()));
            System.out.println("order_number = " + entry.getOrder_number());
            System.out.println("payment_type = " + entry.getPayment_type());
        });;
    }
}
