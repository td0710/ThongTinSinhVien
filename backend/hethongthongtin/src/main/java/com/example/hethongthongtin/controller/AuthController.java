package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.response.GiayXacNhanResponse;
import com.example.hethongthongtin.service.AuthService;
import com.example.hethongthongtin.service.GiayXacNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/get-all")
    public ResponseEntity<?> getById(@RequestBody String id) {
        return ResponseEntity.ok(authService.login(id)) ;
    }
}
