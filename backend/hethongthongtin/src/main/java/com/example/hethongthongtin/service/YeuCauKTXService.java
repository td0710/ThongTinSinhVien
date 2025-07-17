package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.response.PhongResponse;
import com.example.hethongthongtin.dto.response.YeuCauPhongResponse;

import java.util.List;

public interface YeuCauKTXService {

    List<YeuCauPhongResponse> getYeuCauPhongSV(Long userId);

}
