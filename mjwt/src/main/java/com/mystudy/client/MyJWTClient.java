package com.mystudy.client;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * @author dalaoban
 * @create 2020-07-08-23:09
 */
public class MyJWTClient {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNzYiLCJzdWIiOiJsaXV4aW4iLCJpYXQiOjE1OTUwNTYwNzQsImV4cCI6MTU5NTA1Nzg3NCwiYXVkIjoiand0IERlbW8ifQ.Q1pwrfZgSkMGtv4C8eBe1YaZmkf2ld4tOntNu4G1h1k" ;
        String salt = "dalaoban";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(salt).parseClaimsJws(token);

        System.out.println(claimsJws);
    }
}

