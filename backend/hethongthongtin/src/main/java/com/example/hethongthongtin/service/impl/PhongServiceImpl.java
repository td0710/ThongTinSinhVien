package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.entity.Phong;
import com.example.hethongthongtin.repository.PhongRepository;
import com.example.hethongthongtin.service.PhongService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PhongServiceImpl implements PhongService {

    private PhongRepository phongRepository;

    PhongServiceImpl(PhongRepository phongRepository) {
        this.phongRepository = phongRepository;
    }

    @Override
    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }
}
