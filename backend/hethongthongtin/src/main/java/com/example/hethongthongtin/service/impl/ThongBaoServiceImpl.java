package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.request.SearchThongBaoRequest;
import com.example.hethongthongtin.dto.response.ThongBaoResponse;
import com.example.hethongthongtin.entity.ThongBao;
import com.example.hethongthongtin.repository.ThongBaoRepository;
import com.example.hethongthongtin.service.ThongBaoService;
import jakarta.transaction.Transactional;
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
    public List<ThongBaoResponse> getAllThongBao() {


        List<ThongBao> thongbaoList = thongBaoRepository.findAll();


        List<ThongBaoResponse> thongBaoResponseList = thongbaoList.stream().
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
            return thongBaoResponseList;
    }

    @Override
    public List<ThongBaoResponse> searchThongBao(SearchThongBaoRequest searchThongBaoRequest) {

        System.out.println(searchThongBaoRequest.getStartDate());
        System.out.println(searchThongBaoRequest.getEndDate());
        List<ThongBao> thongbaoList = thongBaoRepository.findBySearch(searchThongBaoRequest.getStartDate()
                                                                    ,searchThongBaoRequest.getEndDate()
                                                                    ,searchThongBaoRequest.getTieuDe());
        List<ThongBaoResponse> thongBaoResponseList = thongbaoList.stream().
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
        return thongBaoResponseList;
    }
}
