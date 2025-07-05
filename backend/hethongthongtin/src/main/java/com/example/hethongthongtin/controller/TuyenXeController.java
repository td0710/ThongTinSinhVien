package com.example.hethongthongtin.controller;

import com.example.hethongthongtin.entity.TuyenXe;
import com.example.hethongthongtin.service.TuyenXeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/tuyenxe")
public class TuyenXeController {

    private TuyenXeService tuyenXeService;
    TuyenXeController(TuyenXeService service) {
        tuyenXeService = service;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TuyenXe>> getAllTxb() {
        return ResponseEntity.ok(tuyenXeService.getAllTuyenXe()) ;
    }
}
