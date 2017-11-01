package ch4aop;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConcertConfig.class);
       // error get bean, why?
        // Performance p = context.getBean(Film.class);
        
        Performance p  = context.getBean(Performance.class);
        
        p.perform();
        context.close();
    }

}
