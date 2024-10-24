// package com.firstapi.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     @Autowired
//     private JwtProvider jwtProvider;

//     @Autowired
//     private UserDetailsServiceImpl userDetailsService; // Service pour charger les détails de l'utilisateur

//     // Méthode pour filtrer les requêtes
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//             throws ServletException, IOException {
//         // Récupère le token depuis l'en-tête Authorization
//         String token = request.getHeader("Authorization");

//         // Vérifie si le token commence par "Bearer "
//         if (token != null && token.startsWith("Bearer ")) {
//             token = token.substring(7); // Supprime "Bearer " du token

//             // Valide le token
//             if (jwtProvider.validateToken(token)) {
//                 // Extrait l'email du token
//                 String email = jwtProvider.getEmailFromToken(token);
//                 // Charge les détails de l'utilisateur à partir de l'email
//                 UserDetails userDetails = userDetailsService.loadUserByUsername(email);

//                 // Crée une authentication
//                 UsernamePasswordAuthenticationToken authentication =
//                         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
//                 // Définit le contexte de sécurité
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }

//         // Continue avec le filtre suivant dans la chaîne
//         chain.doFilter(request, response);
//     }
// }

