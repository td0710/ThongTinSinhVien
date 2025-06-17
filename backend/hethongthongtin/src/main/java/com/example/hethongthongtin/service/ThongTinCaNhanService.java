package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.ThongTinCaNhanDto;
import com.example.hethongthongtin.dto.response.ThongTinCaNhanResponse;

public interface ThongTinCaNhanService {
    ThongTinCaNhanResponse getThongTinCaNhan(Long userId);

    void updateThongTin(ThongTinCaNhanDto thongTinCaNhan);
}
