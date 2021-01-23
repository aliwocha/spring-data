package pl.javastart.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(length = 11, unique = true, nullable = false)
    private String pesel;
    @Column(name = "id_number", length = 10, unique = true, nullable = false)
    private String idNumber;
    @ManyToMany(mappedBy = "clients")
    private List<Device> rentDevices = new ArrayList<>();

    public Client(String firstName, String lastName, String pesel, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.idNumber = idNumber;
    }

    public Client() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public List<Device> getRentDevices() {
        return rentDevices;
    }

    public void setRentDevices(List<Device> rentDevices) {
        this.rentDevices = rentDevices;
    }

    @Override
    public String toString() {
        return "Client [" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", pesel=" + pesel +
                ", idNumber=" + idNumber +
                ']';
    }
}
