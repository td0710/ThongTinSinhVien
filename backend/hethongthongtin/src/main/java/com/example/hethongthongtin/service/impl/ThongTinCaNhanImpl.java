package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.ThongTinCaNhanDto;
import com.example.hethongthongtin.entity.ThongTinCaNhan;
import com.example.hethongthongtin.entity.Users;
import com.example.hethongthongtin.repository.ThongTinCaNhanRepository;
import com.example.hethongthongtin.repository.UserRepository;
import com.example.hethongthongtin.service.ThongTinCaNhanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class ThongTinCaNhanImpl implements ThongTinCaNhanService {

    private final ThongTinCaNhanRepository thongTinCaNhanRepository;
    private final UserRepository userRepository;
    ThongTinCaNhanImpl(ThongTinCaNhanRepository thongTinCaNhanRepository,UserRepository userRepository) {
        this.thongTinCaNhanRepository = thongTinCaNhanRepository;
        this.userRepository = userRepository;
    }

    public ThongTinCaNhan getThongTinCaNhan(Long userId) {
        return thongTinCaNhanRepository.findByUserId(userId);
    }

    public void updateThongTin(ThongTinCaNhanDto thongTinCaNhanDto) {
        Long id = thongTinCaNhanDto.getUserId();
        Optional<Users> optionalUser = userRepository.findById(id) ;

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            ThongTinCaNhan thongTinCaNhan = ThongTinCaNhan.builder()
                    .id(Optional.ofNullable(thongTinCaNhanRepository.findByUserId(id))
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
    }
}
