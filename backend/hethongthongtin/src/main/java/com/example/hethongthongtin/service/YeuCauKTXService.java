package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.response.YeuCauPhongResponse;
import com.example.hethongthongtin.entity.YeuCauKTX;

import java.util.List;

public interface YeuCauKTXService {

    List<YeuCauPhongResponse> getYeuCauPhongSV(Long userId);


    YeuCauKTX dangKyPhong(Long userId, Long phongId);

    void huyYeuCau(Long yeuCauId);

    YeuCauKTX doiPhong(Long phongHienTaiId, Long phongMongMuonId, Long userId);

    YeuCauKTX traPhong(Long userId, Long phongId);

    Boolean yeuCauHienTai(Long userId);

}
