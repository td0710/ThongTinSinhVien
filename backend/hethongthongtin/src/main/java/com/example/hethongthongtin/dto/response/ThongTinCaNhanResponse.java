package com.example.hethongthongtin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongTinCaNhanResponse {

    private String maSinhVien;

    private String hoTen;

    private String khoa;

    private String danToc;

    private String tonGiao;

    private String quocTich;

    private String cccd;

    private LocalDate cccdNgayCap;

    private String cccdNoiCap;

    private LocalDate ngaySinh;

    private String noiSinh;

    private String gioiTinh;

    private String queQuan;

    private String soDienThoai;

    private String email;

    private String hoKhauThanhPho;

    private String hoKhauHuyen;

    private String hoKhauXa;

    private String diaChiBaoTin;

    private String soDienThoaiGiaDinh;

    private String lop;

    private String cccdNguoiGiamHo;

    private String maBaoHiemYTe;

    private String maBaoHiemXaHoi;

    private String tenBo;

    private String namSinhBo;

    private String ngheNghiepBo;

    private String noiLamViecBo;

    private String soDienThoaiBo;

    private String tenMe;

    private String namSinhMe;

    private String ngheNghiepMe;

    private String noiLamViecMe;

    private String soDienThoaiMe;

    private LocalDateTime ngayCapNhat;
}
