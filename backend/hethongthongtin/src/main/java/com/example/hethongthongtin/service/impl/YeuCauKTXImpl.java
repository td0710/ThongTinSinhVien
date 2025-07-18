package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.response.PhongResponse;
import com.example.hethongthongtin.dto.response.YeuCauPhongResponse;
import com.example.hethongthongtin.entity.*;
import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;
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

    private final YeuCauKTXRepository yeuCauKTXRepository;
    private final ThongTinCaNhanRepository thongTinCaNhanRepository;
    private final PhongRepository phongRepository;

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

        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }

        if(thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.MSV_NOT_FOUND) ;
        }
        List<YeuCauKTX> yeuCauKTX = yeuCauKTXRepository.findByMaSinhVien(thongTinCaNhan.getMaSinhVien());

        return yeuCauKTX.stream()
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
                    
                    if(item.getLoaiYeuCauKTX().getLabel().equals("Đổi phòng")) {

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
    }

    @Override
    public YeuCauKTX dangKyPhong(Long userId, Long phongId) {

       ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }

        if(thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.MSV_NOT_FOUND) ;
        }

       Phong phong = phongRepository.findById(phongId)
               .orElseThrow(() -> new AppException(ErrorCode.PHONG_NOT_FOUND));


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

        if(!yeuCauKTXRepository.existsById(yeuCauId)) {
            throw new AppException(ErrorCode.REQUEST_NOT_FOUND) ;
        }

        yeuCauKTXRepository.deleteById(yeuCauId);
    }

    @Override
    public YeuCauKTX doiPhong(Long phongHienTaiId, Long phongMongMuonId, Long userId) {

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }

        if(thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.MSV_NOT_FOUND) ;
        }

        Phong phongHienTai = phongRepository.findById(phongHienTaiId)
                .orElseThrow(() -> new AppException(ErrorCode.PHONG_NOT_FOUND));
        Phong phongMongMuon = phongRepository.findById(phongMongMuonId)
                .orElseThrow(() -> new AppException(ErrorCode.PHONG_NOT_FOUND));

        YeuCauKTX yeuCauKTX = YeuCauKTX.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .loaiYeuCauKTX(LoaiYeuCauKTX.DoiPhong)
                .trangThai(TrangThai.DangTiepNhan)
                .phongHienTai(phongHienTai)
                .phongMongMuon(phongMongMuon)
                .build();

        yeuCauKTXRepository.save(yeuCauKTX);

        return yeuCauKTX;
    }

    @Override
    public YeuCauKTX traPhong(Long userId, Long phongId) {

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }

        if(thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.MSV_NOT_FOUND) ;
        }

        Phong phong = phongRepository.findById(phongId)
                .orElseThrow(() -> new AppException(ErrorCode.PHONG_NOT_FOUND)) ;

        YeuCauKTX yeuCauKTX = YeuCauKTX.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .loaiYeuCauKTX(LoaiYeuCauKTX.TraPhong)
                .trangThai(TrangThai.DangTiepNhan)
                .phongHienTai(phong)
                .build();

        yeuCauKTXRepository.save(yeuCauKTX);

        return yeuCauKTX;
    }

    @Override
    public Boolean yeuCauHienTai(Long userId) {

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }

        if(thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.MSV_NOT_FOUND) ;
        }

        List<YeuCauKTX> yeuCauKTXList = yeuCauKTXRepository.findByMaSinhVien(thongTinCaNhan.getMaSinhVien());

        if(yeuCauKTXList.isEmpty()) return false;

        return yeuCauKTXList.stream()
                                        .anyMatch((item) -> (!item.getTrangThai().equals(TrangThai.HoanThanh)
                                                && !item.getTrangThai().equals(TrangThai.TuChoi)));
    }
}
