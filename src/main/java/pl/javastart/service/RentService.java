package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.entity.Client;
import pl.javastart.entity.Device;
import pl.javastart.exceptions.RentException;
import pl.javastart.repository.ClientRepository;
import pl.javastart.repository.DeviceRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RentService {

    private final Scanner scanner;
    private final DeviceRepository deviceRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public RentService(Scanner scanner, DeviceRepository deviceRepository, ClientRepository clientRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Metodę rentDeviceToClient() musimy oznaczyć adnotacją @Transactional, ponieważ chcemy,
     * żeby przy commicie transakcji (wywołaniu metody) zostały uwzględnione zmiany,
     * których dokonaliśmy w obiektach Client i Device pobranych w metodzie rentDevice().
     */

    @Transactional
    public void rentDeviceToClient() {
        try {
            rentDevice();
        } catch(RentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rentDevice() {
        System.out.println("Podaj id klienta, który wypożycza urządzenie:");
        Long clientId = scanner.nextLong();
        System.out.println("Podaj id urządzenia do wypożyczenia:");
        Long deviceId = scanner.nextLong();

        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Device> device = deviceRepository.findById(deviceId);

        if(client.isPresent()) {
            device.ifPresentOrElse(dev -> {
                if(dev.getQuantity() > dev.getClients().size())
                    dev.addClient(client.get());
                else
                    throw new RentException("Brak wolnych urządzeń o wskazanym id");
            }, () -> {
                throw new RentException("Brak urządzenia o wskazanym id");
            });
        } else {
            throw new RentException("Brak klienta o wskazanym id");
        }
        System.out.println("Wypożyczono urządzenie o id=" + deviceId + " klientowi o id=" + clientId);
    }
}
