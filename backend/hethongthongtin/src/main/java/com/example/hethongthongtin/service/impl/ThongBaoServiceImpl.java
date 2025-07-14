package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.request.SearchThongBaoRequest;
import com.example.hethongthongtin.dto.response.ThongBaoPageResponse;
import com.example.hethongthongtin.dto.response.ThongBaoResponse;
import com.example.hethongthongtin.entity.ThongBao;
import com.example.hethongthongtin.repository.ThongBaoRepository;
import com.example.hethongthongtin.service.ThongBaoService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ThongBaoServiceImpl implements ThongBaoService {

    ThongBaoRepository thongBaoRepository;

    ThongBaoServiceImpl(ThongBaoRepository thongBaoRepository) {
        this.thongBaoRepository = thongBaoRepository;
    }

    @Override
    public ThongBaoPageResponse getAllThongBao(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page thongBaoPage = thongBaoRepository.findAll(pageable);

        List<ThongBao> thongBaoList = thongBaoPage.getContent();

        List<ThongBaoResponse> thongBaoResponseList = thongBaoList.stream().
                map((item) -> {
                    ThongBaoResponse thongBaoResponse = new ThongBaoResponse();
                    thongBaoResponse.setId(item.getId());
                    thongBaoResponse.setNoiDung(item.getNoiDung());
                    thongBaoResponse.setTieuDe(item.getTieuDe());
                    thongBaoResponse.setDanhSachFileDinhKem(item.getDanhSachFileDinhKem());
                    thongBaoResponse.setNguoiDang(item.getUser().getFull_name());
                    thongBaoResponse.setNgayDang(item.getNgayDang());
                    return thongBaoResponse;
                })
                .collect(Collectors.toList());

        ThongBaoPageResponse thongBaoPageResponse = ThongBaoPageResponse.builder()
                .thongBao(thongBaoResponseList)
                .pageNo(thongBaoPage.getNumber())
                .totalElements(thongBaoPage.getTotalElements())
                .totalPages(thongBaoPage.getTotalPages())
                .pageSize(thongBaoPage.getSize())
                .last(thongBaoPage.isLast())
                .build();

        return thongBaoPageResponse;
    }

    @Override
    public ThongBaoPageResponse searchThongBao(int page, int size,
                                               SearchThongBaoRequest searchThongBaoRequest) {

        Pageable pageable = PageRequest.of(page, size);

        Page thongBaoPage= thongBaoRepository.findBySearch(
                pageable,
                searchThongBaoRequest.getStartDate()
                ,searchThongBaoRequest.getEndDate()
                ,searchThongBaoRequest.getTieuDe());

        List<ThongBao> thongBaoList = thongBaoPage.getContent();

        List<ThongBaoResponse> thongBaoResponseList = thongBaoList.stream().
                map((item) -> {
                    ThongBaoResponse thongBaoResponse = new ThongBaoResponse();
                    thongBaoResponse.setId(item.getId());
                    thongBaoResponse.setNoiDung(item.getNoiDung());
                    thongBaoResponse.setTieuDe(item.getTieuDe());
                    thongBaoResponse.setDanhSachFileDinhKem(item.getDanhSachFileDinhKem());
                    thongBaoResponse.setNguoiDang(item.getUser().getFull_name());
                    thongBaoResponse.setNgayDang(item.getNgayDang());
                    return thongBaoResponse;
                })
                .collect(Collectors.toList());

        ThongBaoPageResponse thongBaoPageResponse = ThongBaoPageResponse.builder()
                .thongBao(thongBaoResponseList)
                .pageNo(thongBaoPage.getNumber())
                .totalElements(thongBaoPage.getTotalElements())
                .totalPages(thongBaoPage.getTotalPages())
                .pageSize(thongBaoPage.getSize())
                .last(thongBaoPage.isLast())
                .build();

        return thongBaoPageResponse;
    }

    @Override
    public Long totalThongBao() {
        return thongBaoRepository.count() ;
    }
}
