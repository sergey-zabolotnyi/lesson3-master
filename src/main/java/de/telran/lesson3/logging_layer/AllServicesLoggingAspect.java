package de.telran.lesson3.logging_layer;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AllServicesLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);
    @Pointcut("execution(* de.telran.lesson3.service_layer.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logMethodBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Метод {} класса {} вызван с такими параметрами: {}",
                method, className, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logMethodAfterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        LOGGER.info("Метод {} класса {} выполнен с такими результатами: {}", method, className, result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logMethodAErrors(JoinPoint joinPoint, Exception error) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        LOGGER.error("Метод {} класса {} вызвал ошибку: {}", method, className, error.getMessage());
    }

    @After("serviceMethods()")
    public void logMethodAfter(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        LOGGER.info("Метод {} класса {} закончил свое выполнение.", method, className);
    }
}
