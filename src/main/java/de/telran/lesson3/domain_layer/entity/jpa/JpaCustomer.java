package de.telran.lesson3.domain_layer.entity.jpa;

import de.telran.lesson3.domain_layer.entity.Cart;
import de.telran.lesson3.domain_layer.entity.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, max = 15, message = "Customer name length must be between 3 and 15")
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$")
    private String name;

    @Column(name = "age")
    @NotBlank(message = "Аге must be not blank")
    @Min(value = 14)
    @Max(value = 99)
    private int age;

    @Email(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
    @NotBlank(message = "Email must be not blank")
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private JpaCart cart;

    public JpaCustomer() {
    }

    public JpaCustomer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public JpaCustomer(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
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
    public int getAge() {
        return age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}