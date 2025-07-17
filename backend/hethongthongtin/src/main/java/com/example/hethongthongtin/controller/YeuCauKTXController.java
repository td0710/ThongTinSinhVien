package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.response.YeuCauPhongResponse;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.YeuCauKTXService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/yeucauktx")
public class YeuCauKTXController {

    private YeuCauKTXService yeucauKTXService;
    private JWTGenerator jwtGenerator;

    YeuCauKTXController(YeuCauKTXService yeucauKTXService, JWTGenerator jwtGenerator) {
        this.yeucauKTXService = yeucauKTXService;
        this.jwtGenerator = jwtGenerator;
    }


    @GetMapping("/get-by-id")
    public ResponseEntity<List<YeuCauPhongResponse>> getYeuCauPhongById(@RequestParam Long id) {

        return ResponseEntity.ok(yeucauKTXService.getYeuCauPhongSV(id));
    }
}
