package com.example.comptecqrses;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompteCqrsEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteCqrsEsApplication.class, args);
    }
    @Bean
    public SimpleCommandBus axonServerCommandBus(){
        return SimpleCommandBus.builder().build();
    }
}
