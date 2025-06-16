package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.ThongTinCaNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongTinCaNhanRepository extends JpaRepository<ThongTinCaNhan, Long> {
    ThongTinCaNhan findByUserId(Long userId);
}
