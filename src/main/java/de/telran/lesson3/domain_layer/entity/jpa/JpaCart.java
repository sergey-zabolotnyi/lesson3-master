package de.telran.lesson3.domain_layer.entity.jpa;

import de.telran.lesson3.domain_layer.entity.Cart;
import de.telran.lesson3.domain_layer.entity.Product;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class JpaCart implements Cart {
    public static final Logger LOGGER = LoggerFactory.getLogger(JpaCart.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private JpaCustomer customer;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<JpaProduct> products;

    public JpaCart() {
        LOGGER.info(String.format("The empty constructor is applied"));
    }

    public JpaCart(JpaCustomer customer) {
        LOGGER.info(String.format("The constructor with customer name - %s is applied", customer.getName()));
        this.customer = customer;
    }

    public void setProducts(List<JpaProduct> products) {
        this.products = products;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public void addProduct(Product product) {
        products.add(new JpaProduct(product.getId(), product.getName(), product.getPrice()));
    }

    public void removeProduct(JpaProduct product) {
        products.remove(product);
    }

    public void clear() {
        products.clear();
    }

    @Override
    public double getTotalPrice() {
        return products.stream().mapToDouble(JpaProduct::getPrice).sum();
    }
}