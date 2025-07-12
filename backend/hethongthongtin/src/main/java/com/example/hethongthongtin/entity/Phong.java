package com.example.hethongthongtin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "phong")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_phong", length = 50, nullable = false, unique = true)
    private String tenPhong;

    @Column(name = "loai_phong", length = 50)
    private String loaiPhong;

    @Column(name = "so_sv")
    private Integer soSv;

    @Column(name = "gia")
    private Long gia;

    @Column(name = "so_luong_da_dang_ky")
    private Long soLuongDaDangKy;

    @ManyToMany
    @JoinTable(
            name = "phong_tien_ich",
            joinColumns = @JoinColumn(name = "phong_id"),
            inverseJoinColumns = @JoinColumn(name = "tien_ich_id")
    )
    private List<TienIch> tienIchList;
}
