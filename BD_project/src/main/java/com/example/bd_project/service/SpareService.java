package com.example.bd_project.service;

import com.example.bd_project.dao.SpareDao;
import com.example.bd_project.entity.Spare;
import com.example.bd_project.entity.SpareKey;
import org.hibernate.Session;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class SpareService implements Service {
    private final SpareDao dao;

    public SpareService(Session session) {
        dao = new SpareDao(session);
    }

    @Override
    public void addRecord() {
        Spare spare = new Spare();
        spare.setKey(readKey());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter stash_count(int):");
        spare.setStash_count(scanner.nextInt());

        dao.add(spare);
    }

    @Override
    public void deleteRecord() {
        dao.delete(readKey());
    }

    private SpareKey readKey() {
        Scanner scanner = new Scanner(System.in);

        SpareKey key = new SpareKey();
        System.out.println("Enter name(string):");
        key.setName(scanner.next());
        System.out.println("Enter model(string):");
        key.setModel(scanner.next());
        System.out.println("Enter type(string):");
        key.setType(scanner.next());

        return key;
    }

    @Override
    public void showRecord() {
        dao.show().forEach(entry -> {
            System.out.println("name = " + entry.getKey().getName());
            System.out.println("model = " + entry.getKey().getModel());
            System.out.println("type = " + entry.getKey().getType());

            System.out.println("stash_count = " + entry.getStash_count());
        });

    }

    @Override
    public void changeRecord() {

        SpareKey key = readKey();
        Optional<Spare> spare = dao.show()
                .stream()
                .filter(entry -> Objects.equals(entry.getKey().getName(), key.getName()) &&
                        Objects.equals(entry.getKey().getModel(), key.getModel()) &&
                        Objects.equals(entry.getKey().getType(), key.getType()))
                .findAny();
        if (spare.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Change name = " + spare.get().getKey().getName() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            spare.get().getKey().setName(scanner.next());
        }
        System.out.println("Change model = " + spare.get().getKey().getModel() + "?");
        check = scanner.nextBoolean();
        if (check) {
            spare.get().getKey().setModel(scanner.next());
        }
        System.out.println("Change type = " + spare.get().getKey().getType() + "?");
        check = scanner.nextBoolean();
        if (check) {
            spare.get().getKey().setType(scanner.next());
        }

        System.out.println("Change stash_count = " + spare.get().getStash_count() + "?");
        check = scanner.nextBoolean();
        if (check) {
            spare.get().setStash_count(scanner.nextInt());
        }
        dao.change(spare.get(), key);
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}
