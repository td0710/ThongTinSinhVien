package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.request.SearchThongBaoRequest;
import com.example.hethongthongtin.dto.response.ThongBaoPageResponse;
import com.example.hethongthongtin.dto.response.ThongBaoResponse;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.ThongBaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/thongbao")
public class ThongBaoController {
    private ThongBaoService thongBaoService;
    private JWTGenerator jwtGenerator;
    ThongBaoController(ThongBaoService thongBaoService,
                        JWTGenerator jwtGenerator) {
        this.thongBaoService = thongBaoService;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/get-all")
    public ResponseEntity<ThongBaoPageResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(thongBaoService.getAllThongBao(page, size)) ;
    }

    @PostMapping("/search")
    public ResponseEntity<ThongBaoPageResponse> search(@RequestParam int page, @RequestParam int size,
                                                       @RequestBody SearchThongBaoRequest searchThongBaoRequest) {
        return ResponseEntity.ok(thongBaoService.searchThongBao(page,size,searchThongBaoRequest));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> totalThongBao() {
        return ResponseEntity.ok(thongBaoService.totalThongBao());
    }

}
