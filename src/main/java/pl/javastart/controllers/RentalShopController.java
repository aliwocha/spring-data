package pl.javastart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.javastart.service.CategoryService;
import pl.javastart.service.ClientService;
import pl.javastart.service.DeviceService;
import pl.javastart.service.RentService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class RentalShopController {
    private static final int UNDEFINED = 0;
    private static final int ADD_DEVICE = 1;
    private static final int ADD_CATEGORY = 2;
    private static final int ADD_CLIENT = 3;
    private static final int RENT_DEVICE = 4;
    private static final int DELETE_DEVICE = 5;
    private static final int DELETE_CATEGORY = 6;
    private static final int DELETE_CLIENT = 7;
    private static final int CLOSE_APP = 8;

    private final Scanner scanner;
    private final RentService rentService;
    private final ClientService clientService;
    private final DeviceService deviceService;
    private final CategoryService categoryService;

    @Autowired
    public RentalShopController(Scanner scanner, RentService rentService, ClientService clientService,
                                DeviceService deviceService, CategoryService categoryService) {
        this.scanner = scanner;
        this.rentService = rentService;
        this.clientService = clientService;
        this.deviceService = deviceService;
        this.categoryService = categoryService;
    }

    public void mainLoop() {
        System.out.println("Witaj w wypożyczalni sprzętu budowlanego!");
        int option = UNDEFINED;
        while(option != CLOSE_APP) {
            printOptions();
            option = chooseOption();
            executeOption(option);
        }
    }

    private void printOptions() {
        System.out.println("Opcje:");
        System.out.println("1 - Dodaj urządzenie");
        System.out.println("2 - Dodaj kategorię");
        System.out.println("3 - Dodaj klienta");
        System.out.println("4 - Wypożycz urządzenie");
        System.out.println("5 - Usuń urządzenie");
        System.out.println("6 - Usuń kategorię");
        System.out.println("7 - Usuń klienta");
        System.out.println("8 - Koniec");
        System.out.println("Podaj ID opcji:");
    }

    private int chooseOption() {
        int option;
        try {
            option = scanner.nextInt();
        } catch(InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        if(option > UNDEFINED && option <= CLOSE_APP)
            return option;
        else
            return UNDEFINED;
    }

    private void executeOption(int option) {
        switch(option) {
            case ADD_DEVICE:
                deviceService.addDevice();
                break;
            case ADD_CATEGORY:
                categoryService.addCategory();
                break;
            case ADD_CLIENT:
                clientService.addClient();
                break;
            case RENT_DEVICE:
                rentService.rentDeviceToClient();
                break;
            case DELETE_DEVICE:
                deviceService.deleteDevice();
                break;
            case DELETE_CATEGORY:
                categoryService.deleteCategory();
                break;
            case DELETE_CLIENT:
                clientService.deleteClient();
                break;
            case CLOSE_APP:
                close();
                break;
            default:
                System.out.println("Opcja niezdefiniowana");
        }
    }

    private void close() {
        scanner.close();
        System.out.println("Bye bye!");
    }
}
