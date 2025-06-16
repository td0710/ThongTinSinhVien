package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.ThongTinCaNhanDto;
import com.example.hethongthongtin.entity.ThongTinCaNhan;

public interface ThongTinCaNhanService {
    ThongTinCaNhan getThongTinCaNhan(Long userId);

    void updateThongTin(ThongTinCaNhanDto thongTinCaNhan);
}
