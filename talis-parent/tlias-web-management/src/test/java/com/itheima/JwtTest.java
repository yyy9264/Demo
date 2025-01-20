package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /*
    生成jwt令牌
     */
    @Test
    public void testGenerateToken(){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id","100");
        map.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")//指定签名算法和秘钥
                .addClaims(map)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间
                .compact();
        System.out.println(jwt);
    }
    /*
    解析jwt令牌
     */
    @Test
    public void testParseToken(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMTAwIiwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTczNDY4NjY5Mn0.yUVWauiaMSK2ue4HpONS-Cq3OSYDp3S5CSYEURc0cp0";
        Claims claims = Jwts.parser().setSigningKey("aXRoZWltYQ==")//指定秘钥
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
