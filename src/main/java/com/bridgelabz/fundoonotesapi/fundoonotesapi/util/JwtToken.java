package com.bridgelabz.fundoonotesapi.fundoonotesapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtToken {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secretToken = "SecretKey";

    public String getDataFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretToken).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String field) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, field);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        String compact = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretToken).compact();
        return compact;
    }

    public Boolean validateToken(String token, String email) {
        final String username = getDataFromToken(token);
        return (username.equals(email) && !isTokenExpired(token));
    }
}
