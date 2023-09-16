package de.telran.lesson3.logging_layer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProductServiceLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* de.telran.lesson3.service_layer.jpa.JpaProductService.*(..))")
    public void productServiceMethods() {}

    @Before("productServiceMethods()")
    public void logMethodStart(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Метод {} вызван с параметрами: {}", method, Arrays.toString(args));
    }
    @After("productServiceMethods()")
    public void logMethodEnd(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        LOGGER.info("Метод {} завершил свою работу", method);
    }
}
