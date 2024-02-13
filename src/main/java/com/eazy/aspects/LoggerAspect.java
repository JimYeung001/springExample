package com.eazy.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Around("execution(* com.eazy.services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().toString() + " method execute start");
        Instant start = Instant.now();
        Object value = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute method : " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end.");
        return value;
    }

    @Around("@annotation(com.eazy.aspects.LogAspect)")
    public Object logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().toString() + " method execute start");
        Instant start = Instant.now();
        Object value = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute method : " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end.");
        return value;
    }

    @AfterThrowing(value = "execution(* com.eazy.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.log(Level.SEVERE, joinPoint.getSignature() + " an Exception thrown with the help of " +
                " @AfterThrowing which occurred due to " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* com.eazy.services.*.*(..))", returning = "retVal")
    public void logStatus(JoinPoint joinPoint, Object retVal) {
        logger.log(Level.INFO, joinPoint.getSignature() + " Method successfully processed with the status " +
                retVal.toString());
    }
}
