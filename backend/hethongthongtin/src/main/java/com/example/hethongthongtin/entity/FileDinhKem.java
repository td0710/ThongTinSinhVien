package com.example.hethongthongtin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_dinh_kem")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"thongBao"})
public class FileDinhKem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_file", nullable = false)
    private String tenFile;

    @Column(name = "duong_dan", nullable = false)
    private String duongDan;

    @ManyToOne
    @JoinColumn(name = "thong_bao_id")
    @JsonBackReference
    private ThongBao thongBao;

    @Column(name = "ngay_tao", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime ngayDang;
}
