package org.example.crudspringfjv.ui.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.crudspringfjv.service.UserService;
import org.example.crudspringfjv.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class TokenController {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public TokenController(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestParam String nombre,@RequestParam String password){
        boolean isAuthenticated = userService.login(nombre, password);

        if (isAuthenticated) {
            String token = jwtUtils.generateToken(nombre);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String nombre,@RequestParam String password){
        userService.register(nombre,password);
        boolean isRegistered = userService.register(nombre, password);

        if (isRegistered) {
            return ResponseEntity.ok("Usuario registrado con éxito");
        } else {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
    }

}
