package service;

import com.example.bd_project.dao.StatusChangeDao;
import com.example.bd_project.entity.StatusChange;
import com.example.bd_project.util.DateParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class StatusChangeService implements Service {
    private final StatusChangeDao dao;

    public StatusChangeService() {
        dao = new StatusChangeDao();
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
        Optional<StatusChange> employees = dao.show()
                .stream()
                .filter(entry -> entry.getNumber() == key)
                .findAny();
        if (employees.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change number = " + employees.get().getNumber() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            employees.get().setNumber(scanner.nextInt());
        }
        System.out.println("Change cause = " + employees.get().getCause() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setCause(scanner.next());
        }
        System.out.println("Change position = " + employees.get().getPosition() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setPosition(scanner.next());
        }
        System.out.println("Change order_date = " + DateParser.getString(employees.get().getOrder_date()) + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setOrder_date(DateParser.readDate());
        }
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}