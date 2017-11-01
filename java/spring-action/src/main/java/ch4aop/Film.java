package ch4aop;

import org.springframework.stereotype.Component;

@Component
public class Film implements Performance {

    @Override
    public void perform() {
        // TODO Auto-generated method stub
        System.out.println("Film is perform ...");
    }

}
