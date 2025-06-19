package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.entity.TuyenXe;
import com.example.hethongthongtin.repository.TuyenXeRepository;
import com.example.hethongthongtin.service.TuyenXeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TuyenXeImpl implements TuyenXeService {

    private TuyenXeRepository tuyenXeRepository;

    TuyenXeImpl(TuyenXeRepository tuyenXeRepository) {
        this.tuyenXeRepository = tuyenXeRepository;
    }

    @Override
    public List<TuyenXe> getAllTuyenXe() {
        List<TuyenXe> tuyenXe= tuyenXeRepository.findAll();
        return tuyenXe;
    }
}
