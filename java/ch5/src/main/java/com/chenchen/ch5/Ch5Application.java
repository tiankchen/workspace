package com.chenchen.ch5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch5Application {

    @RequestMapping("/")
    String index() {
        return "hello spring boot";
    }
    
    
    
	public static void main(String[] args) {
		//SpringApplication.run(Ch5Application.class, args);
	    SpringApplication app = new SpringApplication(Ch5Application.class);
	    //app.setShowBanner(false);  //not found?
	    app.run(args);
	}
}
