package com.Tsoft.UniClub.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtilHeplers {


    @Value("${jwt.privateKey}")
    private String privateKey;//Stores the private key used for signing the JWT.

    private long expiredTime = 60 * 60 * 1000;

    public String generateToken(String data) {
    // biến key kiểu string đã lưu trữ trước đó thành Secreckey
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Date d = new Date();// gét currenttime then sub with expiredTime that you want to set
        long currentDateMilis  = d.getTime()+expiredTime;

        //System.out.println("meo meo "+currentDateMilis);
        Date expDate = new Date(currentDateMilis);// This is the time for expiration correctly

        String jwt = Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .setExpiration(expDate)
                .compact();
        System.out.println("token "+jwt);
        return jwt;
    }

    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        return Jwts.parser().verifyWith(key).build().parseClaimsJws(token).getPayload().getSubject();
    }
}
