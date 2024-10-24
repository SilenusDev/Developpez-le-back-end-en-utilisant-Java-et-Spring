package com.firstapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Service pour charger les utilisateurs

    @Autowired
    private JwtFilter jwtFilter; // Filtre pour le JWT

    // Méthode pour configurer l'authentification
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // Définit le service d'utilisateur et l'encodeur de mot de passe
    }

    // Définit l'encodeur de mot de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilise BCrypt pour l'encodage
    }

    // Méthode pour configurer la sécurité HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Désactive la protection CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Utilise une politique de session sans état
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() // Autorise l'accès public aux endpoints d'authentification
                .anyRequest().authenticated(); // Nécessite une authentification pour toutes les autres requêtes

        // Ajoute le filtre JWT avant le filtre UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // Méthode pour configurer le gestionnaire d'authentification
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); // Retourne le gestionnaire d'authentification
    }
}
