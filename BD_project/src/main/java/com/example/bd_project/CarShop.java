package com.example.bd_project;


import com.example.bd_project.service.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CarShop {
    private final Map<Integer, Service> serviceMap = new HashMap<>();;
    public CarShop() {
        serviceMap.put(1, new BuyersService());
        serviceMap.put(2, new BuyLicensService());
        serviceMap.put(3, new CarService());
        serviceMap.put(4, new EmployeesService());
        serviceMap.put(5, new PaymentTypeService());
        serviceMap.put(6, new SellersService());
        serviceMap.put(7, new SpareService());
        serviceMap.put(8, new StatusChangeService());
    }

    void run() throws IOException, ParseException {
        while (true) {
            System.out.println(String.join("Select table or exit:\n",
                    "0. exit\n",
                    "1. Buyers\n",
                    "2. BuyLicens\n",
                    "3. Car\n",
                    "4. Employees\n",
                    "5. PaymentType\n",
                    "6. Sellers\n",
                    "7. Spare\n",
                    "8. StatusChange"));
            Scanner scanner = new Scanner(System.in);
            int count = scanner.nextInt();
            if (count == 0) {
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

    void workService(Service service) throws IOException, ParseException {
        System.out.println(String.join("Select event:\n",
                "0. exit\n",
                "1. Add\n",
                "2. Delete\n",
                "3. Show\n",
                "4. Change"));
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        if (count < 0 || count > 4) {
            System.out.println("Incorrect number");
            run();
            return;
        }
        switch (count) {
            case 1 -> service.addRecord();
            case 2 -> service.deleteRecord();
            case 3 -> service.showRecord();
            case 4 -> service.changeRecord();
            default -> {}
        }
    }
}