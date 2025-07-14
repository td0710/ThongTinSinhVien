package com.example.hethongthongtin.controller;


import com.example.hethongthongtin.dto.request.SearchPhongRequest;
import com.example.hethongthongtin.dto.response.PhongPageResponse;
import com.example.hethongthongtin.service.PhongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/phong")
public class PhongController {

    private PhongService phongService;
    PhongController(PhongService phongService) {
        this.phongService = phongService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<PhongPageResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(phongService.findAllByPage(page,size)) ;
    }

    @PostMapping("/search")
    public ResponseEntity<PhongPageResponse> getBySearch(@RequestParam int page
            , @RequestParam int size, @RequestBody SearchPhongRequest searchPhongRequest) {
        return ResponseEntity.ok(phongService.findAllBySearch(page,size,searchPhongRequest)) ;
    }
}
