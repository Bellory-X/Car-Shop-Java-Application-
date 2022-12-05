package com.example.bd_project.service;

import com.example.bd_project.dao.SellersDao;
import com.example.bd_project.entity.Sellers;
import com.example.bd_project.util.DateParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class SellersService implements Service {
    private final SellersDao dao;

    public SellersService() {
        dao = new SellersDao();
    }

    @Override
    public void addRecord() throws IOException, ParseException {
        Sellers sellers = new Sellers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter passport_number(int):");
        sellers.setPassport_number(scanner.nextInt());
        System.out.println("Enter license_plate(string):");
        sellers.setLicense_plate(scanner.next());
        System.out.println("Enter buy_date(date format: yyyy/MM/dd):");
        sellers.setBuy_date(DateParser.readDate());
        System.out.println("Enter id_buy_license(long):");
        sellers.setId_buy_license(scanner.nextLong());

        dao.add(sellers);
    }

    @Override
    public void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter passport_number(int):");
        int key = scanner.nextInt();
        dao.delete(key);
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("passport_number = " + entry.getPassport_number());
            System.out.println("license_plate = " + entry.getLicense_plate());
            System.out.println("buy_date = " + DateParser.getString(entry.getBuy_date()));
            System.out.println("id_buy_license = " + entry.getId_buy_license());
        });

    }

    @Override
    public void changeRecord() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        Optional<Sellers> sellers = dao.show()
                .stream()
                .filter(entry -> entry.getPassport_number() == key)
                .findAny();
        if (sellers.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change passport_number = " + sellers.get().getPassport_number() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            sellers.get().setPassport_number(scanner.nextInt());
        }
        System.out.println("Change license_plate = " + sellers.get().getLicense_plate() + "?");
        check = scanner.nextBoolean();
        if (check) {
            sellers.get().setLicense_plate(scanner.next());
        }
        System.out.println("Change buy_date = " + DateParser.getString(sellers.get().getBuy_date()) + "?");
        check = scanner.nextBoolean();
        if (check) {
            sellers.get().setBuy_date(DateParser.readDate());
        }
        System.out.println("Change id_buy_license = " + sellers.get().getId_buy_license() + "?");
        check = scanner.nextBoolean();
        if (check) {
            sellers.get().setId_buy_license(scanner.nextLong());
        }
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}
