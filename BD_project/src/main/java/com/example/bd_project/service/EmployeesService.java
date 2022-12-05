package com.example.bd_project.service;

import com.example.bd_project.dao.EmployeesDao;
import com.example.bd_project.entity.Employees;
import com.example.bd_project.entity.EmployeesKey;
import com.example.bd_project.util.DateParser;
import org.hibernate.Session;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class EmployeesService implements Service {
    private final EmployeesDao dao;

    public EmployeesService(Session session) {
        dao = new EmployeesDao(session);
    }

    @Override
    public void addRecord() throws IOException, ParseException {
        Employees employees = new Employees();
        employees.setKey(readKey());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter position(string):");
        employees.setPosition(scanner.next());
        System.out.println("Enter salary(long):");
        employees.setSalary(scanner.nextLong());
        System.out.println("Enter change_status(int):");
        employees.setChange_status(scanner.nextInt());

        dao.add(employees);
    }

    @Override
    public void deleteRecord() throws IOException, ParseException {
        dao.delete(readKey());
    }

    private EmployeesKey readKey() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        EmployeesKey employeesKey = new EmployeesKey();
        System.out.println("Enter second_name(string):");
        employeesKey.setSecond_name(scanner.next());
        System.out.println("Enter first_name(string):");
        employeesKey.setFirst_name(scanner.next());
        System.out.println("Enter patronymic(string):");
        employeesKey.setPatronymic(scanner.next());
        System.out.println("Enter birthday(date format: yyyy/MM/dd):");
        employeesKey.setBirthday(DateParser.readDate());

        return employeesKey;
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("second_name = " + entry.getKey().getSecond_name());
            System.out.println("first_name = " + entry.getKey().getFirst_name());
            System.out.println("patronymic = " + entry.getKey().getPatronymic());
            System.out.println("birthday = " + DateParser.getString(entry.getKey().getBirthday()));

            System.out.println("position = " + entry.getPosition());
            System.out.println("salary = " + entry.getSalary());
            System.out.println("change_status = " + entry.getChange_status());
        });

    }

    @Override
    public void changeRecord() throws IOException, ParseException {

        EmployeesKey key = readKey();
        Optional<Employees> employees = dao.show()
                .stream()
                .filter(entry -> entry.getKey().getSecond_name().equals(key.getSecond_name()) &&
                        entry.getKey().getFirst_name().equals(key.getFirst_name()) &&
                        entry.getKey().getPatronymic().equals(key.getPatronymic()) &&
                        entry.getKey().getBirthday().getTime() == key.getBirthday().getTime())
                .findAny();
        if (employees.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println(employees.get().getKey().getBirthday());
        System.out.println(key.getBirthday());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Change second_name = " + employees.get().getKey().getSecond_name() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setSecond_name(scanner.next());
        }
        System.out.println("Change first_name = " + employees.get().getKey().getFirst_name() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setFirst_name(scanner.next());
        }
        System.out.println("Change patronymic = " + employees.get().getKey().getPatronymic() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setPatronymic(scanner.next());
        }
        System.out.println("Change birthday = " + DateParser.getString(employees.get().getKey().getBirthday()) + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setBirthday(DateParser.readDate());
        }

        System.out.println("Change position = " + employees.get().getPosition() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setPosition(scanner.next());
        }
        System.out.println("Change salary = " + employees.get().getSalary() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setSalary(scanner.nextLong());
        }
        System.out.println("Change change_status = " + employees.get().getChange_status() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setChange_status(scanner.nextInt());
        }
        dao.change(employees.get(), key);
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}
