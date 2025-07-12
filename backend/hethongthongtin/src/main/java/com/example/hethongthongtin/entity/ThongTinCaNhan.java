package com.example.hethongthongtin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sinh_vien")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongTinCaNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "ma_sinh_vien", unique = true, nullable = true, length = 20)
    private String maSinhVien;

    @Column(name = "ho_ten", nullable = true, length = 100)
    private String hoTen;

    @Column(name = "khoa", length = 50)
    private String khoa;

    @Column(name = "dan_toc", length = 50)
    private String danToc;

    @Column(name = "ton_giao", length = 50)
    private String tonGiao;

    @Column(name = "quoc_tich", length = 50)
    private String quocTich;

    @Column(name = "cccd", length = 12)
    private String cccd;

    @Column(name = "cccd_ngay_cap")
    private LocalDate cccdNgayCap;

    @Column(name = "cccd_noi_cap", length = 100)
    private String cccdNoiCap;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "noi_sinh", length = 100)
    private String noiSinh;

    @Column(name = "gioi_tinh", columnDefinition = "ENUM('Nam','Nữ')")
    private String gioiTinh;

    @Column(name = "que_quan", length = 100)
    private String queQuan;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "ho_khau_thanh_pho", length = 100)
    private String hoKhauThanhPho;

    @Column(name = "ho_khau_huyen", length = 100)
    private String hoKhauHuyen;

    @Column(name = "ho_khau_xa", length = 100)
    private String hoKhauXa;

    @Column(name = "dia_chi_bao_tin", length = 255)
    private String diaChiBaoTin;

    @Column(name = "so_dien_thoai_gia_dinh", length = 15)
    private String soDienThoaiGiaDinh;

    @Column(name = "lop", length = 20)
    private String lop;

    @Column(name = "cccd_nguoi_giam_ho", length = 12)
    private String cccdNguoiGiamHo;

    @Column(name = "ma_bao_hiem_y_te", length = 15)
    private String maBaoHiemYTe;

    @Column(name = "ma_bao_hiem_xa_hoi", length = 15)
    private String maBaoHiemXaHoi;

    @Column(name = "ten_bo", length = 100)
    private String tenBo;

    @Column(name = "nam_sinh_bo", length = 4)
    private String namSinhBo;

    @Column(name = "nghe_nghiep_bo", length = 100)
    private String ngheNghiepBo;

    @Column(name = "noi_lam_viec_bo", length = 100)
    private String noiLamViecBo;

    @Column(name = "so_dien_thoai_bo", length = 15)
    private String soDienThoaiBo;

    @Column(name = "ten_me", length = 100)
    private String tenMe;

    @Column(name = "nam_sinh_me", length = 4)
    private String namSinhMe;

    @Column(name = "nghe_nghiep_me", length = 100)
    private String ngheNghiepMe;

    @Column(name = "noi_lam_viec_me", length = 100)
    private String noiLamViecMe;

    @Column(name = "so_dien_thoai_me", length = 15)
    private String soDienThoaiMe;

    @Column(name = "ngay_cap_nhat", nullable = true)
    private LocalDateTime ngayCapNhat;


}
