package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.request.ThongTinCaNhanDto;
import com.example.hethongthongtin.dto.response.ThongTinCaNhanResponse;
import com.example.hethongthongtin.entity.ThongTinCaNhan;
import com.example.hethongthongtin.service.ThongTinCaNhanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/thongtincanhan")
public class ThongTinCaNhanController {
    private ThongTinCaNhanService thongTinCaNhanService;

    public ThongTinCaNhanController(ThongTinCaNhanService thongTinCaNhanService) {
        this.thongTinCaNhanService = thongTinCaNhanService;
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<ThongTinCaNhanResponse> getById(@RequestParam Long id) {
        return ResponseEntity.ok(thongTinCaNhanService.getThongTinCaNhan(id)) ;
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateThongTin(@RequestBody ThongTinCaNhanDto thongTinCaNhanDto) {
        thongTinCaNhanService.updateThongTin(thongTinCaNhanDto);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
