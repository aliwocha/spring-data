package pl.javastart.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(length = 1024)
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Device> devices = new HashSet<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {}

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

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Category [" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ']';
    }
}
