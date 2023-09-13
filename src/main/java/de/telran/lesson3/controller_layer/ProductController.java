package de.telran.lesson3.controller_layer;

import de.telran.lesson3.domain_layer.entity.common.CommonProduct;
import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.exception_layer.Response;
import de.telran.lesson3.exception_layer.exceptions.EntityValidationException;
import de.telran.lesson3.exception_layer.exceptions.FirstTestException;
import de.telran.lesson3.exception_layer.exceptions.SecondTestException;
import de.telran.lesson3.exception_layer.exceptions.ThirdTestException;
import de.telran.lesson3.service_layer.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements Controller {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll() {
        List<Product> products = service.getAll();
        if (products.size() == 7) {
            throw new ThirdTestException("Список продуктов пуст!");
        }
        return products;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Product add(@Valid @RequestBody CommonProduct product) {
        try {
            service.add(product);
            return product;
        } catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/deletename/{name}")
    public void delete(@PathVariable String name) {
        service.deleteByName(name);
    }

    @GetMapping("/count")
    public int getCount() {
        return service.getCount();
    }

    @GetMapping("/total")
    public double getTotalPrice() {
        return service.getTotalPrice();
    }

    @GetMapping("/average")
    public double getAverage() {
        return service.getAveragePrice();
    }
}