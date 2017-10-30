package ch2.testbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PlayerConfig.class);
        Player player = context.getBean(Player.class);
        player.Play();
       
        context.close();
    }

}
