package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.response.PhongResponse;
import com.example.hethongthongtin.dto.response.YeuCauPhongResponse;
import com.example.hethongthongtin.entity.*;
import com.example.hethongthongtin.repository.PhongRepository;
import com.example.hethongthongtin.repository.ThongTinCaNhanRepository;
import com.example.hethongthongtin.repository.YeuCauKTXRepository;
import com.example.hethongthongtin.service.YeuCauKTXService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class YeuCauKTXImpl implements YeuCauKTXService {

    private YeuCauKTXRepository yeuCauKTXRepository;
    private ThongTinCaNhanRepository thongTinCaNhanRepository;
    private PhongRepository phongRepository;

    YeuCauKTXImpl(YeuCauKTXRepository yeuCauKTXRepository,
                  ThongTinCaNhanRepository thongTinCaNhanRepository,
                  PhongRepository phongRepository
                  ) {
        this.yeuCauKTXRepository = yeuCauKTXRepository;
        this.thongTinCaNhanRepository = thongTinCaNhanRepository;
        this.phongRepository = phongRepository;
    }

    @Override
    public List<YeuCauPhongResponse> getYeuCauPhongSV(Long userId) {

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        List<YeuCauKTX> yeuCauKTX = yeuCauKTXRepository.findByMaSinhVien(thongTinCaNhan.getMaSinhVien());


        List<YeuCauPhongResponse> yeuCauPhongResponses = yeuCauKTX.stream()
                .map((item) -> {
                    YeuCauPhongResponse yeuCauPhongResponse= new YeuCauPhongResponse();

                    yeuCauPhongResponse.setId(item.getId());
                    yeuCauPhongResponse.setLoaiYeuCau(item.getLoaiYeuCauKTX().getLabel());
                    yeuCauPhongResponse.setTrangThai(item.getTrangThai().getLabel());

                    Phong phong = item.getPhongHienTai() ;

                    PhongResponse phongHienTai = PhongResponse.builder()
                            .id(phong.getId())
                            .gia(phong.getGia())
                            .loaiPhong(phong.getLoaiPhong())
                            .soLuongDaDangKy(phong.getDanhSachSinhVien().size())
                            .soSv(phong.getSoSv())
                            .tienIchList(phong.getTienIchList())
                            .tenPhong(phong.getTenPhong())
                    .build();
                    
                    if(item.getLoaiYeuCauKTX().getLabel() == "Đổi phòng") {

                        Phong phongMongMuon = item.getPhongMongMuon() ;

                        PhongResponse phongMongMuonResponse = PhongResponse.builder()
                                .id(phongMongMuon.getId())
                                .gia(phongMongMuon.getGia())
                                .loaiPhong(phongMongMuon.getLoaiPhong())
                                .soLuongDaDangKy(phongMongMuon.getDanhSachSinhVien().size())
                                .soSv(phongMongMuon.getSoSv())
                                .tienIchList(phongMongMuon.getTienIchList())
                                .tenPhong(phongMongMuon.getTenPhong())
                                .build();
                        yeuCauPhongResponse.setPhongMongMuon(phongMongMuonResponse);

                    }
                    yeuCauPhongResponse.setPhongHienTai(phongHienTai);
                    return yeuCauPhongResponse;
                })
                .collect(Collectors.toList()) ;

        return yeuCauPhongResponses;
    }

    @Override
    public YeuCauKTX dangKyPhong(Long userId, Long phongId) {

       ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

       Phong phong = phongRepository.findById(phongId).get() ;
        System.out.println(thongTinCaNhan);
        System.out.println(phong);
        YeuCauKTX yeuCauKTX = YeuCauKTX.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .loaiYeuCauKTX(LoaiYeuCauKTX.DangKy)
                .trangThai(TrangThai.DangTiepNhan)
                .phongHienTai(phong)
                .build();

        yeuCauKTXRepository.save(yeuCauKTX);

        return yeuCauKTX;
    }

    @Override
    public void huyYeuCau(Long yeuCauId) {
        yeuCauKTXRepository.deleteById(yeuCauId);
    }
}
