package de.telran.lesson3.service_layer.jpa;

import de.telran.lesson3.domain_layer.entity.Cart;
import de.telran.lesson3.domain_layer.entity.Customer;
import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.domain_layer.entity.jpa.JpaCart;
import de.telran.lesson3.domain_layer.entity.jpa.JpaCustomer;
import de.telran.lesson3.repository_layer.jpa.JpaCartRepository;
import de.telran.lesson3.repository_layer.jpa.JpaCustomerRepository;
import de.telran.lesson3.repository_layer.jpa.JpaProductRepository;
import de.telran.lesson3.service_layer.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaCustomerService implements CustomerService {

    @Autowired
    private JpaCustomerRepository repository;

    @Autowired
    private JpaCartRepository cartRepository;

    @Autowired
    private JpaProductRepository productRepository;

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Customer getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(Customer customer) {
        JpaCustomer savedCustomer = repository.save(new JpaCustomer(0, customer.getName()));
        cartRepository.save(new JpaCart(savedCustomer));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public int getCount() {
        return (int) repository.count();
    }

    @Override
    public double getTotalPriceById(int id) {
        return getById(id).getCart().getTotalPrice();
    }

    @Override
    public double getAveragePriceById(int id) {
        Cart cart = getById(id).getCart();
        return cart.getTotalPrice() / cart.getProducts().size();
    }

    @Transactional
    @Override
    public void addToCartById(int customerId, int productId) {
        Customer customer = repository.findById(customerId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        Cart cart = customer.getCart();
        cart.addProduct(product);
    }

    @Transactional
    @Override
    public void deleteFromCart(int customerId, int productId) {
        ((JpaCart) getById(customerId).getCart()).removeProduct(productRepository.findById(productId).orElse(null));
    }

    @Transactional
    @Override
    public void clearCart(int customerId) {
        ((JpaCart) getById(customerId).getCart()).clear();
    }
}