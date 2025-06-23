package com.example.hethongthongtin.controller;

import com.example.hethongthongtin.dto.request.YeuCauGiayXacNhanDto;
import com.example.hethongthongtin.dto.response.YeuCauGiayXacNhanResponse;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.YeuCauGiayXacNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/yeucaugiayxacnhan")
public class YeuCauGiayXacNhanController {
    private YeuCauGiayXacNhanService yeuCauGiayXacNhanService;
    private JWTGenerator jwtGenerator;

    YeuCauGiayXacNhanController(YeuCauGiayXacNhanService yeuCauGiayXacNhanService,
                                JWTGenerator jwtGenerator) {
        this.yeuCauGiayXacNhanService = yeuCauGiayXacNhanService;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<YeuCauGiayXacNhanResponse>> getAll(@CookieValue("jwt") String token) {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);
        return ResponseEntity.ok(yeuCauGiayXacNhanService.getByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGXN(@RequestBody YeuCauGiayXacNhanDto yeuCauGiayXacNhanDto,
                                       @CookieValue("jwt") String token) {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);
        yeuCauGiayXacNhanService.createYeuCauGiayXacNhan(yeuCauGiayXacNhanDto,userId);
        return ResponseEntity.ok("Yêu cầu giấy xác nhận thành công") ;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        yeuCauGiayXacNhanService.deleteYeuCauGiayXacNhan(id);
        return ResponseEntity.ok("Hủy yêu cầu thành công") ;
    }
}
