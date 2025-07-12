package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.SearchThongBaoRequest;
import com.example.hethongthongtin.dto.response.ThongBaoResponse;

import java.util.List;

public interface ThongBaoService {

    List<ThongBaoResponse> getAllThongBao();

    List<ThongBaoResponse> searchThongBao(SearchThongBaoRequest searchThongBaoRequest);

}
