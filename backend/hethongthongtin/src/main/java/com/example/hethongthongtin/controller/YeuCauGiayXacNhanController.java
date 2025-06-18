package com.example.hethongthongtin.controller;

import com.example.hethongthongtin.dto.request.YeuCauGiayXacNhanDto;
import com.example.hethongthongtin.dto.response.YeuCauGiayXacNhanResponse;
import com.example.hethongthongtin.service.YeuCauGiayXacNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/yeucaugiayxacnhan")
public class YeuCauGiayXacNhanController {
    private YeuCauGiayXacNhanService yeuCauGiayXacNhanService;

    YeuCauGiayXacNhanController(YeuCauGiayXacNhanService yeuCauGiayXacNhanService) {
        this.yeuCauGiayXacNhanService = yeuCauGiayXacNhanService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<YeuCauGiayXacNhanResponse>> getAll(@RequestParam Long userId) {
        return ResponseEntity.ok(yeuCauGiayXacNhanService.getByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> getAll(@RequestBody YeuCauGiayXacNhanDto yeuCauGiayXacNhanDto) {
        yeuCauGiayXacNhanService.createYeuCauGiayXacNhan(yeuCauGiayXacNhanDto);
        return ResponseEntity.ok("Yêu cầu giấy xác nhận thành công") ;
    }
}
