package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.PhongSinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongSinhVienRepository extends JpaRepository<PhongSinhVien, Long> {
    PhongSinhVien findByPhongId(Long phongId);
}
