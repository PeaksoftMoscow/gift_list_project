package com.peaksoft.config;

import com.peaksoft.entityUser.UserG;
import com.peaksoft.repository.UserDetailsRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .mvcMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable();
        }

        @Bean
        public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
            return map -> {
                String id = (String) map.get("sub");

                UserG user = userDetailsRepo.findById(id).orElseGet(() -> {
                    UserG newUser = new UserG();

                    newUser.setId(id);
                    newUser.setFirstName((String) map .get("first name"));
                    newUser.setLastName((String) map.get("last name"));
                    newUser.setEmail((String) map.get("email"));

                    return newUser;
                });


                return userDetailsRepo.save(user);
            };
        }
    }


