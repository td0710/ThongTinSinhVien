package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.response.YeuCauPhongResponse;
import com.example.hethongthongtin.entity.YeuCauKTX;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.YeuCauKTXService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/yeucauktx")
public class YeuCauKTXController {

    private YeuCauKTXService yeucauKTXService;
    private JWTGenerator jwtGenerator;

    YeuCauKTXController(YeuCauKTXService yeucauKTXService, JWTGenerator jwtGenerator) {
        this.yeucauKTXService = yeucauKTXService;
        this.jwtGenerator = jwtGenerator;
    }


    @GetMapping("/get-by-id")
    public ResponseEntity<List<YeuCauPhongResponse>> getYeuCauPhongById(@CookieValue("jwt") String token) {

        Long userId = jwtGenerator.extractUserIdFromJwt(token);

        return ResponseEntity.ok(yeucauKTXService.getYeuCauPhongSV(userId));
    }

    @PostMapping("/dang-ky-phong")
    public ResponseEntity<YeuCauKTX> dangKyPhong(@CookieValue("jwt") String token, @RequestParam Long phongId) {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);

        return ResponseEntity.ok(yeucauKTXService.dangKyPhong(userId, phongId));
    }

    @DeleteMapping("/huy-yeu-cau")
    public ResponseEntity<?> huyYeuCau(@RequestParam Long yeuCauId) {

        yeucauKTXService.huyYeuCau(yeuCauId);

        return ResponseEntity.ok("Hủy yêu cầu thành công!") ;
    }

    @PostMapping("/doi-phong")
    public ResponseEntity<YeuCauKTX> doiPhong(@CookieValue("jwt") String token,
                                                 @RequestParam Long phongHienTaiId,
                                                 @RequestParam Long phongMongMuonId) {

        Long userId = jwtGenerator.extractUserIdFromJwt(token);

        return ResponseEntity.ok(yeucauKTXService.doiPhong(phongHienTaiId, phongMongMuonId, userId));
    }

    @PostMapping("/tra-phong")
    public ResponseEntity<YeuCauKTX> traPhong(@CookieValue("jwt") String token,
                                              @RequestParam Long phongId) {

        Long userId = jwtGenerator.extractUserIdFromJwt(token);

        return ResponseEntity.ok(yeucauKTXService.traPhong(userId, phongId));
    }

    @GetMapping("/yeu-cau-hien-tai")
    public ResponseEntity<Boolean> yeuCauHienTai(@CookieValue("jwt") String token) {

        Long userId = jwtGenerator.extractUserIdFromJwt(token);

        return ResponseEntity.ok(yeucauKTXService.yeuCauHienTai(userId));
    }
}
