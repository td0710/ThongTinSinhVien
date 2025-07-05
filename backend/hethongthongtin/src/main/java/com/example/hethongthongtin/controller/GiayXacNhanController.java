package com.example.hethongthongtin.controller;

import com.example.hethongthongtin.dto.response.GiayXacNhanResponse;
import com.example.hethongthongtin.dto.response.ThongTinCaNhanResponse;
import com.example.hethongthongtin.service.GiayXacNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/giayxacnhan")
public class GiayXacNhanController {
    private final GiayXacNhanService giayXacNhanService;
    public GiayXacNhanController(GiayXacNhanService giayXacNhanService) {
        this.giayXacNhanService = giayXacNhanService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GiayXacNhanResponse>> getAllGxn() {
        return ResponseEntity.ok(giayXacNhanService.getAll()) ;
    }
}
