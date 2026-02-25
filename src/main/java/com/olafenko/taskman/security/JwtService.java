package com.olafenko.taskman.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    //token expiration time in milliseconds
    @Value("${jwt.expirationTime}")
    private int jwtExpirationTime;

    private SecretKey secretKey;

    //initialize secret key variable after object construct to use jwtSecret value which is injected @Value after construct
    @PostConstruct
    private void initSecretKey(){
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //function that generate JWT
    public String generateToken(UserDetails userDetails){

        JwtBuilder jwtBuilder = Jwts.builder()
                .header()
                .type("jwt")
                .and()
                .claims()
                .subject(userDetails.getUsername())
                .add("role", userDetails.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .and()
                .signWith(secretKey);

        return jwtBuilder.compact();
    }



}
