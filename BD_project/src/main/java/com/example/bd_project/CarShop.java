package com.example.bd_project;


import com.example.bd_project.service.*;
import com.example.bd_project.util.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CarShop {
    private final Map<Integer, Service> serviceMap = new HashMap<>();
    private final Session session;
    public CarShop() {
        session = HibernateUtil.getSessionFactory().openSession();
        serviceMap.put(1, new BuyersService(session));
        serviceMap.put(2, new BuyLicensService(session));
        serviceMap.put(3, new CarService(session));
        serviceMap.put(4, new EmployeesService(session));
        serviceMap.put(5, new PaymentTypeService(session));
        serviceMap.put(6, new SellersService(session));
        serviceMap.put(7, new SpareService(session));
        serviceMap.put(8, new StatusChangeService(session));
    }

    public void run() throws IOException, ParseException {
        while (true) {
            System.out.println(String.join("\nSelect table or exit:\n",
                    "0. exit\n",
                    "1. Buyers\n",
                    "2. BuyLicens\n",
                    "3. Car\n",
                    "4. Employees\n",
                    "5. PaymentType\n",
                    "6. Sellers\n",
                    "7. Spare\n",
                    "8. StatusChange\n"));
            Scanner scanner = new Scanner(System.in);
            int count = scanner.nextInt();
            if (count == 0) {
                close();
                return;
            }
            if (count < 0 || count > 8) {
                System.out.println("Incorrect number");
                run();
                return;
            }
            workService(serviceMap.get(count));
        }
    }

    private void workService(Service service) throws IOException, ParseException {
        System.out.println(String.join("\nSelect event:\n",
                "0. exit\n",
                "1. Add\n",
                "2. Delete\n",
                "3. Show\n",
                "4. Change\n",
                "5. Search\n"));
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        if (count < 0 || count > 5) {
            System.out.println("Incorrect number");
            run();
            return;
        }
        switch (count) {
            case 1 -> service.addRecord();
            case 2 -> service.deleteRecord();
            case 3 -> service.showRecord();
            case 4 -> service.changeRecord();
            case 5 -> service.searchRecords();
            default -> {}
        }
    }

    private void close() {
        session.close();
        serviceMap.clear();
    }
}