package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.YeuCauGiayXacNhanRequest;
import com.example.hethongthongtin.dto.response.YeuCauGiayXacNhanResponse;

import java.util.List;

public interface YeuCauGiayXacNhanService {
    void createYeuCauGiayXacNhan(YeuCauGiayXacNhanRequest yeuCauXacNhanDto, Long userId);
    List<YeuCauGiayXacNhanResponse> getByUserId(Long userId) ;
    void deleteYeuCauGiayXacNhan(Long id) ;
}
