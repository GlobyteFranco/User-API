package com.TpSpringboot.User_API.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Para una API REST simple conviene desactivar CSRF
                .csrf(csrf -> csrf.disable())

                // Permitimos acceso libre a los endpoints del TP
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                        .anyRequest().authenticated())

                // Desactivamos login form
                .formLogin(form -> form.disable())

                // Si querés, también podés desactivar httpBasic
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}