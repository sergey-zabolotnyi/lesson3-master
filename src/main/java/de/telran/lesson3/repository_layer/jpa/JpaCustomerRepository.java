package de.telran.lesson3.repository_layer.jpa;

import de.telran.lesson3.domain_layer.entity.jpa.JpaCustomer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<JpaCustomer, Integer> {

    @Transactional
    void deleteByName(String name);
}