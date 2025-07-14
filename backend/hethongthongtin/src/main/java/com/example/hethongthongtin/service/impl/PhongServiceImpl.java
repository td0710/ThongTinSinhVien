package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.response.PhongPageResponse;
import com.example.hethongthongtin.entity.Phong;
import com.example.hethongthongtin.repository.PhongRepository;
import com.example.hethongthongtin.service.PhongService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PhongServiceImpl implements PhongService {

    private PhongRepository phongRepository;

    PhongServiceImpl(PhongRepository phongRepository) {
        this.phongRepository = phongRepository;
    }

    @Override
    public PhongPageResponse findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Phong> phongPage = phongRepository.findAll(pageable);

        List<Phong> phongList = phongPage.getContent();


        PhongPageResponse response = PhongPageResponse.builder()
                .phong(phongList)
                .pageNo(phongPage.getNumber())
                .pageSize(phongPage.getSize())
                .totalPages(phongPage.getTotalPages())
                .totalElements(phongPage.getTotalElements())
                .last(phongPage.isLast())
                .build();

        return response ;
    }
}
