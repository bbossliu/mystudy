package com.mystudy.server;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import sun.plugin2.message.ShowStatusMessage;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-07-08-22:46
 */
public class MyJWTServer {

    public static void getToken(){

        //1、head头部
       Map<String,Object> header= new HashMap<>();
       //-- jwt
        header.put("typ","jwt");
        // -- 加密算法
        header.put("alg","HS256");

        //2、payload 签发者信息 公有声明 私有声明
        Claims claims = new DefaultClaims();
        claims.setId("jwtTest")
              .setSubject("JWT示例")
              .setIssuedAt(new Date()) //签发时间
              .setExpiration(new Date(System.currentTimeMillis() + 1800*1000L))//过期时间
              .setAudience("我的JWT");//描述信信息
        // 3、签名
        String salt = "liuxin" ;
        byte[] saltBase64 = DatatypeConverter.parseBase64Binary(salt);
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;

        SecretKeySpec secretKeySpec = new SecretKeySpec(saltBase64, hs256.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder().setHeader(header).setClaims(claims).signWith(hs256, secretKeySpec);
        String token = jwtBuilder.compact();

        System.out.println(token);

    }

    public static void main(String[] args) {
        getToken();
    }
}
