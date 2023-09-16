package de.telran.lesson3.domain_layer.entity.jpa;

import de.telran.lesson3.domain_layer.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "product")
public class JpaProduct implements Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    // Potato - valid
    // potato - not valid
    // Potato5 - not valid
    // Potato# - not valid
    // Po - not valid
    @Pattern(regexp = "[A-Z][a-z]{2,}")
    private String name;

    @Column(name = "price")
    @NotNull
//    @Min(value = 1 / 100)
    @Min(value = 1)
    @Max(value = 999999)
    private double price;

    public JpaProduct() {
    }

    public JpaProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JpaProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}