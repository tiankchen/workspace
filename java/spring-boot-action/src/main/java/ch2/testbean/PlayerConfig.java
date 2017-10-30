package ch2.testbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfig {

    @Bean
    public Player player1() {
        return new Player();
    }
    
//    @Bean
//    public Player player2() {
//        return new Player();
//    }
}
