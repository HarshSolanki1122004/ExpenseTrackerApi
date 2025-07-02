package com.harvices.project.expense_tracker_api.security;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
/*
creates and validates token
This class will:
🛠 Generate JWTs when user logs in
🔍 Validate JWTs on every request
🧠 Extract username/email from the token
 */
@Component
public class JwtUtils {
    private final String jwtSecret = "8ce728cf29c5493793767337ed8c2b848ce5faaf4f305a1c84f2142db7ab88aa"; // base64-encoded 256-bit key
    private final long jwtExpirationMs = 86400000; // 24 hours

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSecretKey())
                .compact();
    }
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token); // if parsing fails, exception is thrown
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
            return false;
        }
    }
}
