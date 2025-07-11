package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.SearchThongBaoRequest;
import com.example.hethongthongtin.dto.response.ThongBaoResponse;
import com.example.hethongthongtin.entity.ThongBao;

import java.util.List;

public interface ThongBaoService {

    List<ThongBaoResponse> getAllThongBao();

    List<ThongBaoResponse> searchThongBao(SearchThongBaoRequest searchThongBaoRequest);
}
