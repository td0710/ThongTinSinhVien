package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
