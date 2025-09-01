package com.fukuoka.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtUtil {

    // 秘密鍵（※ 256bit以上推奨：32文字以上の英数字を用意）
    private static final String SECRET_KEY = "your-256-bit-secret-key-should-be-long-enough";

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1時間

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey()) // ← 統一
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // ← 統一
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public String validateAndExtractUsername(String token) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // ← 統一
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
