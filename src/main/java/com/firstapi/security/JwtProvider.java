// package com.firstapi.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.Date;

// @Component
// public class JwtProvider {
    
//     private final String SECRET_KEY = "aQGMwWYPcI"; // À remplacer par une clé plus sécurisée
//     private final long EXPIRATION_TIME = 86400000; // 1 jour en millisecondes
    
//     // Création de la clé secrète
//     private final SecretKey key = Keys.hmacShaKeyFor(
//             SECRET_KEY.getBytes(StandardCharsets.UTF_8)
//     );

//     // Méthode pour générer un JWT
//     public String generateToken(String email) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(key)  // La nouvelle méthode signWith prend directement une Key
//                 .compact();
//     }

//     // Méthode pour valider un token JWT
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     // Méthode pour extraire le sujet (email) du token JWT
//     public String getEmailFromToken(String token) {
//         Claims claims = Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//         return claims.getSubject();
//     }
// }

