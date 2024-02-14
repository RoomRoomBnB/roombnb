package com.sparta.roombnb.jwt;

import com.sparta.roombnb.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private Key key;

    public JwtUtil(@Value("${jwt.secret.key}") String secret) {

        byte[] byteSecretKey = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(byteSecretKey);
    }

    public String getUsername(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("username", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    //코드 중복 줄이기
    //사용자 정보 Claims에 설정

    private Claims setClaims(User user) {
        Claims claims = Jwts.claims();
        System.out.println(user.getUsername()+user.getIntroduction());
        Map<String,Object> userInfo = Map.of(
            "id",user.getId(),
            "username",user.getUsername(),
            "email", user.getEmail(),
            "introduction", user.getIntroduction(),
            "photo", user.getPhoto(),
            "password", user.getPassword()
        );
        userInfo.forEach(claims::put);
        return claims;
    }

    //jwt생성
    public String createJwt(User user) {
        Claims claims = setClaims(user);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30000000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public User getUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        User user = new User();
        user.setId(claims.get("id", Long.class));
        user.setUsername(claims.get("username", String.class));
        user.setEmail(claims.get("email", String.class));
        user.setIntroduction(claims.get("introduction", String.class));
        user.setPhoto(claims.get("photo", String.class));
        user.setPassword(claims.get("password", String.class));
        return user;
    }
}
//비밀번호랑 다 깔끔하게 들어가게
//로그인할때 json으로 받아오기 (RequestBody)
