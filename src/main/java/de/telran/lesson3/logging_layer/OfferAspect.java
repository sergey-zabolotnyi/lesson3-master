package de.telran.lesson3.logging_layer;

import de.telran.lesson3.domain_layer.entity.Product;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OfferAspect {

    private Logger logger = LoggerFactory.getLogger(OfferAspect.class);

    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaProductService.getById(..)) && args(id)")
    public void getProductById(int id) {}

    @AfterReturning(value = "getProductById(id)", returning = "product")
    public void sendOffer(int id, Product product) {

        double discount = 5 + (Math.random() * 5); // к 5% скидке прибавляем скидку от 0 до 5%
        double oldPrice = product.getPrice();
        double newPrice = oldPrice - (oldPrice*discount/100);

        logger.info(String.format("Предложение: товар '%s' со скидкой %.2f%%. Старая цена: %.2f, новая цена: %.2f",
                product.getName(), discount, oldPrice, newPrice));
    }
}
