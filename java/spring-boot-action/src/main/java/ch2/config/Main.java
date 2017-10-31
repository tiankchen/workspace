package ch2.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExpressiveConfig.class);
        Disc disc = context.getBean(Disc.class);
        disc.Play();
        context.close();
    }

}
