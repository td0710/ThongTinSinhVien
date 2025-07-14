package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.SearchThongBaoRequest;
import com.example.hethongthongtin.dto.response.ThongBaoPageResponse;
import com.example.hethongthongtin.dto.response.ThongBaoResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThongBaoService {

    ThongBaoPageResponse getAllThongBao(int page, int size);

    ThongBaoPageResponse searchThongBao(int page, int size,
                                        SearchThongBaoRequest searchThongBaoRequest);
    Long totalThongBao();
}
