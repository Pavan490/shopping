package com.example.demo1.service;

import com.example.demo1.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String JwtSecret;

    private final int Max_Time_Valid=60*60*1000;

    private Key generateKey(){
        return Keys.hmacShaKeyFor(JwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public  String generateToken(User userDto){
        Date issuedDate=new Date();
        Date expiryDate=new Date(issuedDate.getTime()+Max_Time_Valid);
        Map<String,Object> tokenData=new HashMap<>();
        tokenData.put("name",userDto.getName());
        tokenData.put("email",userDto.getEmail());
        tokenData.put("role","USER");
       String token= Jwts.builder().claims().add(tokenData).and().
                subject(userDto.getEmail()).issuedAt(issuedDate).expiration(expiryDate).signWith(generateKey())
                .compact();
       return token;
    }

    public Claims getJwtClaim(String token){
        Claims claims=Jwts.parser().verifyWith((SecretKey) generateKey()).build().parseSignedClaims(token).getPayload();
        return claims;
    }
    public Boolean verifyJwtToken(String token){
        Claims claims=getJwtClaim(token);
        Boolean isValid=claims.getExpiration().after(new Date());
        return isValid;
    }

}
