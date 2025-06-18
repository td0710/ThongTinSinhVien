package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yeu_cau_giay_xac_nhan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauGiayXacNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_sinh_vien", nullable = false)
    private String maSinhVien;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_giay", nullable = false)
    private LoaiGiay loaiGiay;

    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime ngayTao;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TrangThai trangThai;

    @Column(name = "ngay_nhan")
    private LocalDate ngayNhan;

    @Column(name = "noi_nhan")
    private String noiNhan;

    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String ghiChu;

    @Column(name = "nguoi_xu_ly")
    private Long nguoiXuLy;

    @Column(name = "ngay_xu_ly")
    private LocalDateTime ngayXuLy;
}
