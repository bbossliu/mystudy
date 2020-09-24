package com.mystudy.jwtweb.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;
import java.util.Date;

/**
 * @author dalaoban
 * @create 2020-07-08-23:22
 */
@Component
@ConfigurationProperties("jwt.config")
public class JWTUtil {
    private String key ;

    private long ttl ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public  String createToke(String id,String subject ,String role){

        String token = Jwts.builder()
                .setId("myjwt")
                .setSubject("我的jwt")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttl))
                .setAudience("测试jwt Springboot")
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("roles", role).compact();
        return token;
    }

    public Claims parseToke(String token){
       return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
