package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.YeuCauVeXeBuyt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YeuCauVeXeBuytRepository extends JpaRepository<YeuCauVeXeBuyt, Long> {

    List<YeuCauVeXeBuyt> findAllByMaSinhVien(String maSinhVien);
    void deleteById(Long id);
}
