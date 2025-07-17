package com.example.hethongthongtin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "phong_sinh_vien")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"phong"})
public class PhongSinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    private Users users;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "phong_id")
    private Phong phong;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    @Enumerated(EnumType.STRING)
    private TrangThaiPhong trangThai;

}
