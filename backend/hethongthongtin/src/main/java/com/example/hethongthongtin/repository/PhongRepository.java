package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.Phong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Long> {

    Page<Phong> findAll(Pageable pageable);
}
