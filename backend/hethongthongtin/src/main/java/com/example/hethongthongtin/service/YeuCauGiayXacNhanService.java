package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.YeuCauGiayXacNhanDto;
import com.example.hethongthongtin.dto.response.YeuCauGiayXacNhanResponse;

import java.util.List;

public interface YeuCauGiayXacNhanService {
    void createYeuCauGiayXacNhan(YeuCauGiayXacNhanDto yeuCauXacNhanDto, Long userId);
    List<YeuCauGiayXacNhanResponse> getByUserId(Long userId) ;
    void deleteYeuCauGiayXacNhan(Long id) ;
}
