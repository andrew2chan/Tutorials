package com.udacity.jdnd.course1;

import com.udacity.jdnd.course1.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public String message() {
        System.out.println("message created");
        return "Hello, Spring!";
    }

    @Bean
    public String uppercaseMessage(MessageService messageService) {
        System.out.println("Uppercase Message bean created");
        return messageService.toUpperCase();
    }

    @Bean
    public String lowercaseMessage(MessageService messageService) {
        System.out.println("Lower case Message bean created");
        return messageService.toLowerCase();
    }
}
