package ch2.testbean;

import org.springframework.stereotype.Component;

//@Component
public class Player {
    private Ball ball;

    public Player(Ball ball) {
        this.ball = ball;
    }
    
    public void Play() {
        System.out.println("I am play ball: " + ball.getNum());
    }
}
