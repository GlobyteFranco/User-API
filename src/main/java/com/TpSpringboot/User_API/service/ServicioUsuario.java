package com.TpSpringboot.User_API.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.TpSpringboot.User_API.dto.LoginDTO;
import com.TpSpringboot.User_API.dto.RegistroDTO;
import com.TpSpringboot.User_API.entity.Usuario;
import com.TpSpringboot.User_API.repository.RepositorioUsuario;

import static org.springframework.http.HttpStatus.*;

@Service
public class ServicioUsuario {

    private final RepositorioUsuario userRepository;
    private final PasswordEncoder passwordEncoder;

    public ServicioUsuario(RepositorioUsuario userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegistroDTO request) {
        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(CONFLICT, "Email already exists");
        }

        // Hashear la contraseña antes de guardar
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        Usuario user = new Usuario();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return "User registered successfully";
    }

    public String loginUser(LoginDTO request) {
        // Buscar usuario por email
        Usuario user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User does not exist"));

        // Comparar password ingresada con la hash guardada
        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new ResponseStatusException(UNAUTHORIZED, "Invalid password");
        }

        return "Login successful";
    }
}
