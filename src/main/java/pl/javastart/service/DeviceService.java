package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.entity.Category;
import pl.javastart.entity.Device;
import pl.javastart.exceptions.CategoryNotFoundException;
import pl.javastart.repository.CategoryRepository;
import pl.javastart.repository.DeviceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceService {

    private final Scanner scanner;
    private final DeviceRepository deviceRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DeviceService(Scanner scanner, DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addDevice() {
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            System.out.println("Dodano urządzenie\n" + device);
        } catch (CategoryNotFoundException e) {
            System.err.println("Urządzenia nie dodano. " + e.getMessage());
        }
    }

    public void searchDevice() {
        System.out.println("Wyszukaj urządzenie wpisując jego nazwę lub jej fragment:");
        List<Device> foundDevices = deviceRepository
                .findALlByNameContainingIgnoreCase(scanner.nextLine());
        if(!foundDevices.isEmpty()) {
            System.out.println("Znalezione urządzenia:");
            foundDevices.forEach(System.out::println);
        } else {
            System.out.println("Brak urządzeń o podanej nazwie");
        }
    }

    private Device readDevice() {
        Device device = new Device();
        System.out.println("Nazwa urządzenia:");
        device.setName(scanner.nextLine());
        System.out.println("Opis urządzenia:");
        device.setDescription(scanner.nextLine());
        System.out.println("Cena urządzenia:");
        device.setPrice(scanner.nextDouble());
        System.out.println("Ilość(szt) urządzeń");
        device.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Kategoria urządzenia:");
        Optional<Category> category = categoryRepository.findByNameIgnoreCase(scanner.nextLine());
        category.ifPresentOrElse(device::setCategory, () -> {
            throw new CategoryNotFoundException("Kategoria o podanej nazwie nie istnieje");
        });
        return device;
    }

    public void deleteDevice() {
        System.out.println("Podaj id urządzenia, które chcesz usunąć:");
        Long categoryId = scanner.nextLong();
        Optional<Device> device = deviceRepository.findById(categoryId);
        device.ifPresentOrElse(deviceRepository::delete, () -> System.out.println("Brak urządzenia o wskazanym id"));
    }
}
