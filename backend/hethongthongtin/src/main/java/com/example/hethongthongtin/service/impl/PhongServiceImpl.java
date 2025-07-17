package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.request.PhongResponse;
import com.example.hethongthongtin.dto.request.SearchPhongRequest;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class PhongServiceImpl implements PhongService {

    private PhongRepository phongRepository;

    PhongServiceImpl(PhongRepository phongRepository) {
        this.phongRepository = phongRepository;
    }

    @Override
    public PhongPageResponse findBySearch(int page, int size, SearchPhongRequest searchPhongRequest) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Phong> phongPage = phongRepository.findAllBySearch(pageable,
                searchPhongRequest.getTen(),
                searchPhongRequest.getLoaiPhong(),
                searchPhongRequest.getSoSv(),
                searchPhongRequest.getStart(),
                searchPhongRequest.getEnd(),
                searchPhongRequest.getTrong()
        );
        List<Phong> phongList = phongPage.getContent();

        List<PhongResponse> phongResponseList = phongList.stream()
        .map((item) -> {
            PhongResponse phongResponse = new PhongResponse();
            phongResponse.setId(item.getId());
            phongResponse.setTenPhong(item.getTenPhong());
            phongResponse.setLoaiPhong(item.getLoaiPhong());
            phongResponse.setSoSv(item.getSoSv());
            phongResponse.setGia(item.getGia());
            phongResponse.setSoLuongDaDangKy(item.getDanhSachSinhVien().size());
            phongResponse.setTienIchList(item.getTienIchList());
            return phongResponse;
        })
                .collect(Collectors.toList());


        PhongPageResponse response = PhongPageResponse.builder()
                .phong(phongResponseList)
                .pageNo(phongPage.getNumber())
                .pageSize(phongPage.getSize())
                .totalPages(phongPage.getTotalPages())
                .totalElements(phongPage.getTotalElements())
                .last(phongPage.isLast())
                .build();
        return response ;
    }

}
