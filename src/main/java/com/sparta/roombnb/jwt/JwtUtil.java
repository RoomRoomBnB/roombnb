package com.sparta.roombnb.jwt;

import com.sparta.roombnb.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

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

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public String createJwt(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("introduction", user.getIntroduction());
        claims.put("photo", user.getPhoto());
        claims.put("role", user.getRole());
        claims.put("password", user.getPassword());
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
        user.setRole(claims.get("role", String.class));
        user.setPassword(claims.get("password", String.class));
        return user;
    }
}
//비밀번호랑 다 깔끔하게 들어가게
//로그인할때 json으로 받아오기 (RequestBody)
