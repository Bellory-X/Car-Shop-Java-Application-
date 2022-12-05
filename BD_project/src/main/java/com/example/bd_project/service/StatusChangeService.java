package com.example.bd_project.service;

import com.example.bd_project.dao.StatusChangeDao;
import com.example.bd_project.entity.StatusChange;
import com.example.bd_project.util.DateParser;
import org.hibernate.Session;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class StatusChangeService implements Service {
    private final StatusChangeDao dao;

    public StatusChangeService(Session session) {
        dao = new StatusChangeDao(session);
    }

    @Override
    public void addRecord() throws IOException, ParseException {
        StatusChange statusChange = new StatusChange();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number(int):");
        statusChange.setPosition(scanner.next());
        System.out.println("Enter cause(string):");
        statusChange.setCause(scanner.next());
        System.out.println("Enter position(string):");
        statusChange.setPosition(scanner.next());
        System.out.println("Enter order_date(date format: yyyy/MM/dd):");
        statusChange.setOrder_date(DateParser.readDate());

        dao.add(statusChange);
    }

    @Override
    public void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number(int):");
        dao.delete(scanner.nextInt());
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("number = " + entry.getNumber());
            System.out.println("cause = " + entry.getCause());
            System.out.println("position = " + entry.getPosition());
            System.out.println("order_date = " + DateParser.getString(entry.getOrder_date()));

        });

    }

    @Override
    public void changeRecord() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number(int):");
        int key = scanner.nextInt();
        Optional<StatusChange> statusChange = dao.show()
                .stream()
                .filter(entry -> entry.getNumber() == key)
                .findAny();
        if (statusChange.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change number = " + statusChange.get().getNumber() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            statusChange.get().setNumber(scanner.nextInt());
        }
        System.out.println("Change cause = " + statusChange.get().getCause() + "?");
        check = scanner.nextBoolean();
        if (check) {
            statusChange.get().setCause(scanner.next());
        }
        System.out.println("Change position = " + statusChange.get().getPosition() + "?");
        check = scanner.nextBoolean();
        if (check) {
            statusChange.get().setPosition(scanner.next());
        }
        System.out.println("Change order_date = " + DateParser.getString(statusChange.get().getOrder_date()) + "?");
        check = scanner.nextBoolean();
        if (check) {
            statusChange.get().setOrder_date(DateParser.readDate());
        }
        dao.change(statusChange.get(), key);
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}
