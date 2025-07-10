package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongBaoRepository extends JpaRepository<ThongBao, Long> {

}