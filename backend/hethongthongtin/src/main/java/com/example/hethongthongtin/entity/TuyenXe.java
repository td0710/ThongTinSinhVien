package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tuyen_xe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuyenXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ma_tuyen")
    private String maTuyen;
}
