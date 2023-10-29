//package com.example.clinickapp.security.jwt;
//
//import com.example.clinickapp.utils.DateUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class JwtService {
//    private static final String key = "sdjfn2n3iunf2309hjn3uenfiun24hwe982h3iuwefkjsdsf23";
//
//    public String generateToken(String userId) {
//        return Jwts.builder().claim("sub", userId)
//                .claim("exp", DateUtil.oneDay())
//                .signWith(SignatureAlgorithm.HS256, key)
//                .compact();
//    }
//    public Object getClaim(String token, String name) {
//        return Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJwt(token)
//                .getBody()
//                .get(name);
//    }
//
//    public boolean validateToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(token).getBody();
//        return claims.getExpiration().after(new Date()) && claims.getSubject() != null;
//    }
////    ----------
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
//        final Claims claims = extractClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//
//    private Claims extractClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(key);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public boolean isValidToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isExpiredToken(token);
//    }
//
//    private boolean isExpiredToken(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//
//}
