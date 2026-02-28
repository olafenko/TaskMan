package com.olafenko.taskman.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
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

        return  Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .signWith(secretKey)
                .compact();

    }

    public Boolean validateToken(String token){

        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException ex){
            System.out.println("TOKEN NOT VALID");
            return false;
        }

    }

    public String extractUsernameFromToken(String token){

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String extractRoleFromToken(String token){

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

}
