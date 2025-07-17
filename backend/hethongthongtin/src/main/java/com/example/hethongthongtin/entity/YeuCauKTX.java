package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "yeu_cau_ktx")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YeuCauKTX {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_sinh_vien", nullable = false)
    private String maSinhVien;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_yeu_cau", nullable = false)
    private LoaiYeuCauKTX loaiYeuCauKTX;

    @ManyToOne
    @JoinColumn(name = "phong_hien_tai_id")
    private Phong phongHienTai;

    @ManyToOne
    @JoinColumn(name = "phong_mong_muon_id")
    private Phong phongMongMuon;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TrangThai trangThai = TrangThai.DangTiepNhan;

    @Column(name = "ngay_tao", updatable = false)
    private LocalDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @ManyToOne
    @JoinColumn(name = "nguoi_xu_ly")
    private Users nguoiXuLy;

    @PrePersist
    protected void onCreate() {
        this.ngayTao = LocalDateTime.now();
        this.ngayCapNhat = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.ngayCapNhat = LocalDateTime.now();
    }
}