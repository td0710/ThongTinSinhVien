package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.request.ThongTinCaNhanRequest;
import com.example.hethongthongtin.dto.response.ThongTinCaNhanResponse;
import com.example.hethongthongtin.entity.ThongTinCaNhan;
import com.example.hethongthongtin.entity.Users;
import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;
import com.example.hethongthongtin.repository.ThongTinCaNhanRepository;
import com.example.hethongthongtin.repository.UserRepository;
import com.example.hethongthongtin.service.ThongTinCaNhanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class ThongTinCaNhanServiceImpl implements ThongTinCaNhanService {

    private final ThongTinCaNhanRepository thongTinCaNhanRepository;
    private final UserRepository userRepository;
    ThongTinCaNhanServiceImpl(ThongTinCaNhanRepository thongTinCaNhanRepository, UserRepository userRepository) {
        this.thongTinCaNhanRepository = thongTinCaNhanRepository;
        this.userRepository = userRepository;
    }

    public ThongTinCaNhanResponse getThongTinCaNhan(Long userId) {
        ThongTinCaNhan thongTinCaNhan =  thongTinCaNhanRepository.findByUserId(userId);

        if(thongTinCaNhan == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND);
        }

        return  ThongTinCaNhanResponse.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .hoTen(thongTinCaNhan.getHoTen())
                .khoa(thongTinCaNhan.getKhoa())
                .danToc(thongTinCaNhan.getDanToc())
                .tonGiao(thongTinCaNhan.getTonGiao())
                .quocTich(thongTinCaNhan.getQuocTich())
                .cccd(thongTinCaNhan.getCccd())
                .cccdNgayCap(thongTinCaNhan.getCccdNgayCap())
                .cccdNoiCap(thongTinCaNhan.getCccdNoiCap())
                .ngaySinh(thongTinCaNhan.getNgaySinh())
                .noiSinh(thongTinCaNhan.getNoiSinh())
                .gioiTinh(thongTinCaNhan.getGioiTinh())
                .queQuan(thongTinCaNhan.getQueQuan())
                .soDienThoai(thongTinCaNhan.getSoDienThoai())
                .email(thongTinCaNhan.getEmail())
                .hoKhauThanhPho(thongTinCaNhan.getHoKhauThanhPho())
                .hoKhauHuyen(thongTinCaNhan.getHoKhauHuyen())
                .hoKhauXa(thongTinCaNhan.getHoKhauXa())
                .diaChiBaoTin(thongTinCaNhan.getDiaChiBaoTin())
                .soDienThoaiGiaDinh(thongTinCaNhan.getSoDienThoaiGiaDinh())
                .lop(thongTinCaNhan.getLop())
                .cccdNguoiGiamHo(thongTinCaNhan.getCccdNguoiGiamHo())
                .maBaoHiemYTe(thongTinCaNhan.getMaBaoHiemYTe())
                .maBaoHiemXaHoi(thongTinCaNhan.getMaBaoHiemXaHoi())
                .tenBo(thongTinCaNhan.getTenBo())
                .namSinhBo(thongTinCaNhan.getNamSinhBo())
                .ngheNghiepBo(thongTinCaNhan.getNgheNghiepBo())
                .noiLamViecBo(thongTinCaNhan.getNoiLamViecBo())
                .soDienThoaiBo(thongTinCaNhan.getSoDienThoaiBo())
                .tenMe(thongTinCaNhan.getTenMe())
                .namSinhMe(thongTinCaNhan.getNamSinhMe())
                .ngheNghiepMe(thongTinCaNhan.getNgheNghiepMe())
                .noiLamViecMe(thongTinCaNhan.getNoiLamViecMe())
                .soDienThoaiMe(thongTinCaNhan.getSoDienThoaiMe())
                .ngayCapNhat(LocalDateTime.now())
                .build();

    }

    public void updateThongTin(ThongTinCaNhanRequest thongTinCaNhanDto, Long userId) {

        Optional<Users> optionalUser = userRepository.findById(userId) ;

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            ThongTinCaNhan thongTinCaNhan = ThongTinCaNhan.builder()
                    .id(Optional.ofNullable(thongTinCaNhanRepository.findByUserId(userId))
                            .map(ThongTinCaNhan::getId)
                            .orElse(null))
                    .user(user)
                    .maSinhVien(thongTinCaNhanDto.getMaSinhVien())
                    .hoTen(thongTinCaNhanDto.getHoTen())
                    .khoa(thongTinCaNhanDto.getKhoa())
                    .danToc(thongTinCaNhanDto.getDanToc())
                    .tonGiao(thongTinCaNhanDto.getTonGiao())
                    .quocTich(thongTinCaNhanDto.getQuocTich())
                    .cccd(thongTinCaNhanDto.getCccd())
                    .cccdNgayCap(thongTinCaNhanDto.getCccdNgayCap())
                    .cccdNoiCap(thongTinCaNhanDto.getCccdNoiCap())
                    .ngaySinh(thongTinCaNhanDto.getNgaySinh())
                    .noiSinh(thongTinCaNhanDto.getNoiSinh())
                    .gioiTinh(thongTinCaNhanDto.getGioiTinh())
                    .queQuan(thongTinCaNhanDto.getQueQuan())
                    .soDienThoai(thongTinCaNhanDto.getSoDienThoai())
                    .email(thongTinCaNhanDto.getEmail())
                    .hoKhauThanhPho(thongTinCaNhanDto.getHoKhauThanhPho())
                    .hoKhauHuyen(thongTinCaNhanDto.getHoKhauHuyen())
                    .hoKhauXa(thongTinCaNhanDto.getHoKhauXa())
                    .diaChiBaoTin(thongTinCaNhanDto.getDiaChiBaoTin())
                    .soDienThoaiGiaDinh(thongTinCaNhanDto.getSoDienThoaiGiaDinh())
                    .lop(thongTinCaNhanDto.getLop())
                    .cccdNguoiGiamHo(thongTinCaNhanDto.getCccdNguoiGiamHo())
                    .maBaoHiemYTe(thongTinCaNhanDto.getMaBaoHiemYTe())
                    .maBaoHiemXaHoi(thongTinCaNhanDto.getMaBaoHiemXaHoi())
                    .tenBo(thongTinCaNhanDto.getTenBo())
                    .namSinhBo(thongTinCaNhanDto.getNamSinhBo())
                    .ngheNghiepBo(thongTinCaNhanDto.getNgheNghiepBo())
                    .noiLamViecBo(thongTinCaNhanDto.getNoiLamViecBo())
                    .soDienThoaiBo(thongTinCaNhanDto.getSoDienThoaiBo())
                    .tenMe(thongTinCaNhanDto.getTenMe())
                    .namSinhMe(thongTinCaNhanDto.getNamSinhMe())
                    .ngheNghiepMe(thongTinCaNhanDto.getNgheNghiepMe())
                    .noiLamViecMe(thongTinCaNhanDto.getNoiLamViecMe())
                    .soDienThoaiMe(thongTinCaNhanDto.getSoDienThoaiMe())
                    .ngayCapNhat(LocalDateTime.now())
                    .build();

                    thongTinCaNhanRepository.save(thongTinCaNhan);
        }
        else {
            throw new AppException(ErrorCode.USER_NOT_FOUND) ;
        }
    }
}
