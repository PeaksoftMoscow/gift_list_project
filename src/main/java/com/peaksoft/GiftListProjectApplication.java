package com.peaksoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class GiftListProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftListProjectApplication.class, args);
        System.out.println("Welcome collegues, project name is Giftlist-M3!");
    }

    @GetMapping("/")
    public String greatingPage(){
        return "<h1> Welcome to iftlist-M3 Application! <h1>";
    }

}
