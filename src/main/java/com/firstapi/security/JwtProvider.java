package com.firstapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    // Secret utilisé pour signer le JWT
    private final String SECRET_KEY = "aQGMwWYPcI"; // Remplace par une clé plus sécurisée
    private final long EXPIRATION_TIME = 86400000; // 1 jour en millisecondes

    // Méthode pour générer un JWT
    public String generateToken(String email) {
        // Crée une nouvelle instance de JwtBuilder
        JwtBuilder builder = Jwts.builder()
                .setSubject(email) // Définit le sujet du token comme l'email
                .setIssuedAt(new Date()) // Définit la date de création
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Définit l'expiration
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY); // Signature avec la clé secrète

        // Retourne le token JWT sous forme de chaîne
        return builder.compact();
    }

    // Méthode pour valider un token JWT
    public boolean validateToken(String token) {
        try {
            // Vérifie le token et retourne true si valide
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Retourne false si le token est invalide
        }
    }

    // Méthode pour extraire le sujet (email) du token JWT
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject(); // Retourne le sujet du token
    }
}

