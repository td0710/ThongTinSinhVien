package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.response.PhongPageResponse;
import com.example.hethongthongtin.entity.Phong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhongService {

    PhongPageResponse findAllByPage(int page, int size);

}
