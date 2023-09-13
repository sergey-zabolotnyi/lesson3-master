package de.telran.lesson3.domain_layer.entity;

public interface Customer {

    int getId();

    String getName();

    int getAge();

    String getEmail();

    Cart getCart();
}