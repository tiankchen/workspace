package ch4aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Audience {

    @Before("execution(** ch4aop.Performance.perform(..))")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }
    
    @Before("execution(** ch4aop.Performance.perform(..))")
    public void takeSeats() {
        System.out.println("Taking seats");
    }
    
    @AfterReturning("execution(** ch4aop.Performance.perform(..))")
    public void applause() {
        System.out.println("Clap! Clap!");
    }
}
