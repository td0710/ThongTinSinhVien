package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.YeuCauGiayXacNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YeuCauGiayXacNhanRepository extends JpaRepository<YeuCauGiayXacNhan,Long> {
    List<YeuCauGiayXacNhan> findYeuCauGiayXacNhanByMaSinhVien(String maSinhVien);
    void deleteById(Long id);
}
