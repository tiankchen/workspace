package ch2.testbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfig {

    @Bean
    public Ball ball() {
        return new Ball(22);
    }
    
    @Bean
    public Player player1(Ball ball) {
        return new Player(ball);
    } 
    
    
//    @Bean
//    public Player player2() {
//        return new Player();
//    }
}
