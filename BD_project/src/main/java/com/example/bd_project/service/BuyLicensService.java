package com.example.bd_project.service;

import com.example.bd_project.dao.BuyLicensDao;
import com.example.bd_project.entity.BuyLicens;
import com.example.bd_project.entity.Sellers;
import com.example.bd_project.util.DateParser;
import org.hibernate.Session;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class BuyLicensService implements Service {
    private final BuyLicensDao dao;

    public BuyLicensService(Session session) {
        dao = new BuyLicensDao(session);
    }

    @Override
    public void addRecord() throws IOException, ParseException {
        BuyLicens buyLicens = new BuyLicens();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id_buy_license(long):");
        buyLicens.setId_buy_license(scanner.nextLong());
        System.out.println("Enter name(string):");
        buyLicens.setName(scanner.next());
        System.out.println("Enter issue_date(date format: yyyy/MM/dd):");
        buyLicens.setIssue_date(DateParser.readDate());
        System.out.println("Enter employee_full_name(string):");
        buyLicens.setEmployee_full_name(scanner.next());

        dao.add(buyLicens);
    }

    @Override
    public void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id_buy_license(long):");
        dao.delete(scanner.nextLong());
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("id_buy_license = " + entry.getId_buy_license());
            System.out.println("name = " + entry.getName());
            System.out.println("issue_date = " + DateParser.getString(entry.getIssue_date()));
            System.out.println("employee_full_name = " + entry.getEmployee_full_name());
        });

    }

    @Override
    public void changeRecord() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter key(long):");
        long key = scanner.nextLong();
        Optional<BuyLicens> buyLicens = dao.show()
                .stream()
                .filter(entry -> entry.getId_buy_license() == key)
                .findAny();
        if (buyLicens.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change id_buy_license = " + buyLicens.get().getId_buy_license() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            buyLicens.get().setId_buy_license(scanner.nextLong());
        }
        System.out.println("Change name = " + buyLicens.get().getName() + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyLicens.get().setName(scanner.next());
        }
        System.out.println("Change issue_date = " + DateParser.getString(buyLicens.get().getIssue_date()) + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyLicens.get().setIssue_date(DateParser.readDate());
        }
        System.out.println("Change employee_full_name = " + buyLicens.get().getEmployee_full_name() + "?");
        check = scanner.nextBoolean();
        if (check) {
            buyLicens.get().setEmployee_full_name(scanner.next());
        }
        dao.change(buyLicens.get(), key);
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
            System.out.println("id_buy_license = " + entry.getId_buy_license());
            System.out.println("name = " + entry.getName());
            System.out.println("issue_date = " + DateParser.getString(entry.getIssue_date()));
            System.out.println("employee_full_name = " + entry.getEmployee_full_name());
        });
    }
}
