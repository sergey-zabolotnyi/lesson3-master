package de.telran.lesson3.repository_layer.jpa;

import de.telran.lesson3.domain_layer.entity.jpa.JpaCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCartRepository extends JpaRepository<JpaCart, Integer> {
}