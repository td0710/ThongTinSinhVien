package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.request.ThongTinCaNhanRequest;
import com.example.hethongthongtin.dto.response.ThongTinCaNhanResponse;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.ThongTinCaNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/thongtincanhan")
public class ThongTinCaNhanController {
    private ThongTinCaNhanService thongTinCaNhanService;
    private JWTGenerator jwtGenerator;

    public ThongTinCaNhanController(ThongTinCaNhanService thongTinCaNhanService,
                                    JWTGenerator jwtGenerator) {
        this.thongTinCaNhanService = thongTinCaNhanService;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/get")
    public ResponseEntity<ThongTinCaNhanResponse> getTtcnById(@CookieValue("jwt") String token) {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);
        return ResponseEntity.ok(thongTinCaNhanService.getThongTinCaNhan(userId)) ;
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateThongTin(@RequestBody ThongTinCaNhanRequest thongTinCaNhanDto,
                                            @CookieValue("jwt") String token) {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);
        thongTinCaNhanService.updateThongTin(thongTinCaNhanDto,userId);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
