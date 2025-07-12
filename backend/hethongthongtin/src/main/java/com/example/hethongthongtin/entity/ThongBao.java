package com.example.hethongthongtin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "thong_bao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThongBao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tieu_de", nullable = false)
    private String tieuDe;

    @Column(name = "noi_dung", nullable = false, columnDefinition = "TEXT")
    private String noiDung;

    @OneToOne
    @JoinColumn(name = "nguoi_dang")
    private Users user;

    @OneToMany(mappedBy = "thongBao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FileDinhKem> danhSachFileDinhKem;

    @Column(name = "ngay_dang", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ngayDang;
}
