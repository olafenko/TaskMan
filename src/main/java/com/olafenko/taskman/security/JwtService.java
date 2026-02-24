package com.olafenko.taskman.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

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
    public String generateToken(){


        return null;
    }



}
