package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.entity.Client;
import pl.javastart.repository.ClientRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ClientService {

    private final Scanner scanner;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(Scanner scanner, ClientRepository clientRepository) {
        this.scanner = scanner;
        this.clientRepository = clientRepository;
    }

    public void addClient() {
        Client client = readClient();
        clientRepository.save(client);
        System.out.println("Dodano klienta\n" + client);
    }

    private Client readClient() {
        Client client = new Client();
        System.out.println("Imię klienta:");
        client.setFirstName(scanner.nextLine());
        System.out.println("Nazwisko klienta:");
        client.setLastName(scanner.nextLine());
        System.out.println("Pesel klienta:");
        client.setPesel(scanner.nextLine());
        System.out.println("Numer dowodu osobistego klienta:");
        client.setIdNumber(scanner.nextLine());
        return client;
    }

    public void deleteClient() {
        System.out.println("Podaj id clienta, którego chcesz usunąć:");
        Long clientId = scanner.nextLong();
        Optional<Client> client = clientRepository.findById(clientId);
        client.ifPresentOrElse(clientRepository::delete, () -> System.out.println("Brak klienta o wskazanym id"));
    }
}
