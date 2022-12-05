package com.example.bd_project.service;

import com.example.bd_project.dao.PaymentTypeDao;
import com.example.bd_project.entity.PaymentType;


import java.util.Optional;
import java.util.Scanner;

public class PaymentTypeService implements Service {
    private final PaymentTypeDao dao;

    public PaymentTypeService() {
        dao = new PaymentTypeDao();
    }

    @Override
    public void addRecord() {
        Scanner scanner = new Scanner(System.in);
        PaymentType paymentType = new PaymentType();
        System.out.println("Enter payment_id(int):");
        paymentType.setPayment_id(scanner.nextInt());
        System.out.println("Enter payment_name(string):");
        paymentType.setPayment_name(scanner.next());

        dao.add(paymentType);
    }

    @Override
    public void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment_id(int):");
        dao.delete(scanner.nextInt());
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("payment_id = " + entry.getPayment_id());
            System.out.println("payment_name = " + entry.getPayment_name());
        });

    }

    @Override
    public void changeRecord() {
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        Optional<PaymentType> paymentType = dao.show()
                .stream()
                .filter(entry -> entry.getPayment_id() == key)
                .findAny();
        if (paymentType.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        System.out.println("Change payment_id = " + paymentType.get().getPayment_id() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            paymentType.get().setPayment_id(scanner.nextInt());
        }
        System.out.println("Change payment_name = " + paymentType.get().getPayment_name() + "?");
        check = scanner.nextBoolean();
        if (check) {
            paymentType.get().setPayment_name(scanner.next());
        }

    }

    @Override
    public void closeSession() {
        dao.close();
    }
}

