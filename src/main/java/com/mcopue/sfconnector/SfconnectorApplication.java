package com.mcopue.sfconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:app.yml")
public class SfconnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfconnectorApplication.class, args);
    }

}
