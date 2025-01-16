package com.blog.topicos.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Usuarios simulados (puedes cambiar esto para que venga de una base de datos)
    private static final Map<String, String> USERS = Map.of(
        "admin", "$2a$10$8Y6RE.Pg916dXzb7RXoWPOHXZT.V9JyOONgOn3aCRFVSxVZE3bzIS" // Contraseña: 1234
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = USERS.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        System.out.println("Usuario encontrado: " + username);
        System.out.println("Contraseña encriptada: " + password);

        return User.builder()
            .username(username)
            .password(password)
            .roles("USER") // Asigna un rol al usuario
            .build();
    }

}
