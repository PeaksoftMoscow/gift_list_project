package com.peaksoft.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtTokenUtil {


    private String jwtSecret;

    private final static Long expirationDateInMonth = 30 * 24 * 60 * 60 * 1000l;

        private String createToken(Map<String, Object> claims, String subject) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + expirationDateInMonth))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }

    public String generateToken(UserDetails userDetails) {
    Map<String, Object> objectMap = new HashMap<>();
    return createToken(objectMap,userDetails.getUsername());
        }

 }
