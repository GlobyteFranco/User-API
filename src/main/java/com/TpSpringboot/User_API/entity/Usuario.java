package com.TpSpringboot.User_API.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Constructor vacío requerido por JPA
    public Usuario() {
    }

    // Constructor de conveniencia
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    // IMPORTANTE: acá ya debe venir hasheada
    public void setPassword(String password) {
        this.password = password;
    }
}