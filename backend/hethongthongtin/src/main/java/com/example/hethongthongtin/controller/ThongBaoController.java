package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.response.ThongBaoResponse;
import com.example.hethongthongtin.entity.ThongBao;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.ThongBaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/thongbao")
public class ThongBaoController {
    private ThongBaoService thongBaoService;
    private JWTGenerator jwtGenerator;

    ThongBaoController(ThongBaoService thongBaoService,
                        JWTGenerator jwtGenerator) {
        this.thongBaoService = thongBaoService;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<ThongBaoResponse>> getAll() {
        return ResponseEntity.ok(thongBaoService.getAllThongBao()) ;
    }
}
