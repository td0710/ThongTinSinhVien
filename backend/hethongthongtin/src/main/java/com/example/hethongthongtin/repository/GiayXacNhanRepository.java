package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.GiayXacNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GiayXacNhanRepository extends JpaRepository<GiayXacNhan, Long> {

}
