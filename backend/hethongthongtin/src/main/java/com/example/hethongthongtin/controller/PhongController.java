package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.entity.Phong;
import com.example.hethongthongtin.service.PhongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/phong")
public class PhongController {

    private PhongService phongService;
    PhongController(PhongService phongService) {
        this.phongService = phongService;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Phong>> getAll() {
        return ResponseEntity.ok(phongService.getAllPhong()) ;
    }
}
