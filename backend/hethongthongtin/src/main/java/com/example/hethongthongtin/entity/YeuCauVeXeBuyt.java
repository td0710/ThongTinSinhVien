package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yeu_cau_ve_xe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauVeXeBuyt {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_sinh_vien", length = 20)
    private String maSinhVien;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_ve", length = 20)
    private LoaiVe loaiVe;

    @Column(name = "tuyen")
    private String tuyen;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "duong_dan_anh", length = 255)
    private String duongDanAnh;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", length = 20)
    private TrangThai trangThai;

    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String ghiChu;

    @Column(name = "ngay_nhan")
    private LocalDate ngayNhan;

    @Column(name = "noi_nhan", length = 255)
    private String noiNhan;

    @Column(name = "ngay_yeu_cau")
    private LocalDateTime ngayYeuCau;

    @Column(name = "nguoi_xu_ly")
    private Integer nguoiXuLy;

}
