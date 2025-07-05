package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.request.YeuCauGiayXacNhanDto;
import com.example.hethongthongtin.dto.response.YeuCauGiayXacNhanResponse;
import com.example.hethongthongtin.entity.LoaiGiay;
import com.example.hethongthongtin.entity.ThongTinCaNhan;
import com.example.hethongthongtin.entity.TrangThai;
import com.example.hethongthongtin.entity.YeuCauGiayXacNhan;
import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;
import com.example.hethongthongtin.repository.ThongTinCaNhanRepository;
import com.example.hethongthongtin.repository.YeuCauGiayXacNhanRepository;
import com.example.hethongthongtin.service.YeuCauGiayXacNhanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class YeuCauGiayXacNhanImpl implements YeuCauGiayXacNhanService {

    private YeuCauGiayXacNhanRepository yeuCauGiayXacNhanRepository;
    private ThongTinCaNhanRepository thongTinCaNhanRepository;
    YeuCauGiayXacNhanImpl(YeuCauGiayXacNhanRepository yeuCauGiayXacNhanRepository, ThongTinCaNhanRepository thongTinCaNhanRepository) {
        this.yeuCauGiayXacNhanRepository = yeuCauGiayXacNhanRepository;
        this.thongTinCaNhanRepository = thongTinCaNhanRepository;
    }

    @Override
    public void createYeuCauGiayXacNhan(YeuCauGiayXacNhanDto yeuCauXacNhanDto, Long userId) {
        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId) ;
        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND);
        }
        YeuCauGiayXacNhan yeuCauGiayXacNhan = YeuCauGiayXacNhan.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .loaiGiay(LoaiGiay.fromLabel(yeuCauXacNhanDto.getLoaiGiay()))
                .ngayTao(LocalDateTime.now())
                .trangThai(TrangThai.DangTiepNhan)
                .ghiChu("Yêu cầu mới tạo. Đang chờ xử lý.")
                .build();
        yeuCauGiayXacNhanRepository.save(yeuCauGiayXacNhan) ;
    }

    @Override
    public List<YeuCauGiayXacNhanResponse> getByUserId(Long userId) {
        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId) ;
        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND);
        }
        List<YeuCauGiayXacNhan> yeuCauGiayXacNhan = yeuCauGiayXacNhanRepository
                .findYeuCauGiayXacNhanByMaSinhVien(thongTinCaNhan.getMaSinhVien());

        List<YeuCauGiayXacNhanResponse> yeuCauGiayXacNhanResponses = yeuCauGiayXacNhan
                .stream()
                .map(gxn -> YeuCauGiayXacNhanResponse.builder()
                                .id(gxn.getId())
                                .loaiGiay(gxn.getLoaiGiay().getLabel())
                                .ngayTao(gxn.getNgayTao())
                                .noiNhan(gxn.getNoiNhan())
                                .ghiChu(gxn.getGhiChu())
                                .trangThai(gxn.getTrangThai().getLabel())
                                .ngayNhan(gxn.getNgayNhan())
                                .build()
                        )
                .collect(Collectors.toList());
        System.out.println(yeuCauGiayXacNhanResponses);
        return yeuCauGiayXacNhanResponses;
    }

    @Override
    public void deleteYeuCauGiayXacNhan(Long id) {
        yeuCauGiayXacNhanRepository.deleteById(id);
    }
}
