package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.YeuCauKTX;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YeuCauKTXRepository extends JpaRepository<YeuCauKTX, Long> {

    List<YeuCauKTX> findByMaSinhVien(String maSinhVien);
}
