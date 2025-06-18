package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "giay_xac_nhan")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiayXacNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_giay", nullable = false)
    private LoaiGiay loaiGiay;
}
