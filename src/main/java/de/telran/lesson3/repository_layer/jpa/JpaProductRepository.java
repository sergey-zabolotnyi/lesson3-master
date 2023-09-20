package de.telran.lesson3.repository_layer.jpa;

import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.domain_layer.entity.jpa.JpaProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Integer> {

    @Transactional
    void deleteByName(String name);

    @Query(value = "select sum(price) from product;", nativeQuery = true)
    double getTotalPrice();

    @Query(value = "select avg(price) from product;", nativeQuery = true)
    double getAveragePrice();
    @Query(value = "SELECT name FROM product order by id desc limit 1;", nativeQuery = true)
    String getLastAddedProduct();
}