package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.entity.ThongBao;
import com.example.hethongthongtin.repository.ThongBaoRepository;
import com.example.hethongthongtin.service.ThongBaoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ThongBaoServiceImpl implements ThongBaoService {

    ThongBaoRepository thongBaoRepository;

    ThongBaoServiceImpl(ThongBaoRepository thongBaoRepository) {
        this.thongBaoRepository = thongBaoRepository;
    }

    @Override
    public List<ThongBao> getAllThongBao() {
        return thongBaoRepository.findAll();
    }
}
