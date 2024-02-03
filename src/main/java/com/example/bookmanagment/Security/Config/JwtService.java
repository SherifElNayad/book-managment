package com.example.bookmanagment.Security.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "3fN5oR1jm+Ze/66zlRe2mXAGGqWBMEAXtJq31mW6YcWi/qKuojSPbs51P0KBnLp0zR52xXg7vGjqYbsVwlAp/I5zFlLBlUO4qugluEFvtYyAc2llfZi7+cSyrQFTJoWE50AvXyCbOWbYs7aOvRK1r8l9TKb9UMYLQqNSxq3/7VlRoRBao+LkJPDx5C5ez8lrgtgCeKJ/Kofb09u4GO+2I+banM889uAfZT2x7eEEJaPLRuiD8+LXV8o5l0uydF9yaV3j0oSPY/I8uL8KWvROakYMmOebALjsazqxIhJQLYgBpRGXNz5PTDBh0uVm15eU5RbWn/tXWdIhrMMoBu3qaIQMatNAkFM7gQvr0LtwHTg=";

    public String extractUserName(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(),user);
    }

    public boolean isTokenValid(String token, UserDetails user)
    {
        final String name = extractUserName(token);
        return (name.equals(user.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extractClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
