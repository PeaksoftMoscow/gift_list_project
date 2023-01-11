package com.peaksoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
@RestController

public class GiftListProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiftListProjectApplication.class, args);
    }

    @GetMapping("/")
    public static ArrayList<String> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken, Principal principal)   {
        Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
        ArrayList<String> list = new ArrayList<>();
        System.out.println(principal);
        list.add((String) attributes.get("given_name"));
        return list;
    }
}
