package com.example.hashirun.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// トークン発行機

@Component
public class JwtUtils {
    // 秘密の合言葉
    private final String SECRET_STR = "your-256-bit-secret-key-must-be-long-enough-32-chars";
    // コンピュータが理解できる0と1の塊（バイト配列）」**に変換
    private SecretKey KEY = Keys.hmacShaKeyFor(SECRET_STR.getBytes());
    // トークンの有効期限（24時間）
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // トークンが有効か（偽造されてないか、期限切れじゃないか）チェックする
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // 偽物、または期限切れ
        }
    }

    // トークンから「誰のメアドか」を取り出す
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
