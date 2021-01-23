package pl.javastart.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_device")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 2048)
    private String description;
    private int quantity;
    private double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "device_customers",
            joinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id_device")},
            inverseJoinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id_client")}
    )
    private List<Client> clients = new ArrayList<>();

    public Device(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Device() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        clients.add(client);
        client.getRentDevices().add(this);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Device [" +
                "id=" + id +
                ", name=" + name +
                ", quantity=" + quantity +
                ", price=" + price +
                ']';
    }
}
