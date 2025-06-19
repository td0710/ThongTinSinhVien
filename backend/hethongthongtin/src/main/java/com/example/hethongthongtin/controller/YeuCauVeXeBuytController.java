package com.example.hethongthongtin.controller;

import com.example.hethongthongtin.dto.request.YeuCauVeXeBuytRequest;
import com.example.hethongthongtin.dto.response.YeuCauVeXeBuytResponse;
import com.example.hethongthongtin.service.YeuCauVeXeBuytService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/yeucauvexebuyt")
public class YeuCauVeXeBuytController {

    private final YeuCauVeXeBuytService yeuCauVeXeBuytService;

    YeuCauVeXeBuytController(YeuCauVeXeBuytService yeuCauVeXeBuytService) {
        this.yeuCauVeXeBuytService = yeuCauVeXeBuytService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest
                                    ) throws IOException, GeneralSecurityException {
        yeuCauVeXeBuytService.createYeuCauVeXe(yeuCauVeXeBuytRequest,yeuCauVeXeBuytRequest.getFile());
        return ResponseEntity.ok().build();
    }
}
