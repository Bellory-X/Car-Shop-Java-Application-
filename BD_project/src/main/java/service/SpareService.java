package service;

import com.example.bd_project.dao.SpareDao;
import com.example.bd_project.entity.Spare;
import com.example.bd_project.entity.SpareKey;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class SpareService implements Service {
    private final SpareDao dao;

    public SpareService() {
        dao = new SpareDao();
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
        Optional<Spare> employees = dao.show()
                .stream()
                .filter(entry -> Objects.equals(entry.getKey().getName(), key.getName()) &&
                        Objects.equals(entry.getKey().getModel(), key.getModel()) &&
                        Objects.equals(entry.getKey().getType(), key.getType()))
                .findAny();
        if (employees.isEmpty()) {
            System.out.println("Invalid key");
            changeRecord();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Change name = " + employees.get().getKey().getName() + "?");
        boolean check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setName(scanner.next());
        }
        System.out.println("Change model = " + employees.get().getKey().getModel() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setModel(scanner.next());
        }
        System.out.println("Change type = " + employees.get().getKey().getType() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().getKey().setType(scanner.next());
        }

        System.out.println("Change stash_count = " + employees.get().getStash_count() + "?");
        check = scanner.nextBoolean();
        if (check) {
            employees.get().setStash_count(scanner.nextInt());
        }
    }

    @Override
    public void closeSession() {
        dao.close();
    }
}
