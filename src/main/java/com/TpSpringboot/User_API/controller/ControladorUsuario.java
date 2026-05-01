package com.TpSpringboot.User_API.controller;

import com.TpSpringboot.User_API.dto.LoginDTO;
import com.TpSpringboot.User_API.dto.RegistroDTO;
import com.TpSpringboot.User_API.service.ServicioUsuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class ControladorUsuario {

    private final ServicioUsuario userService;

    public ControladorUsuario(ServicioUsuario userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody RegistroDTO request) {
        String message = userService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", message));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginDTO request) {
        String message = userService.loginUser(request);
        return ResponseEntity.ok(Map.of("message", message));
    }
}