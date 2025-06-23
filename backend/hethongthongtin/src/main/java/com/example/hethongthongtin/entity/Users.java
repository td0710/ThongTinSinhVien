package com.example.hethongthongtin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String microsoft_id;

    @Column(nullable = false, unique = true, length = 50)
    private String full_name;

    @Column(nullable = false, columnDefinition = "ENUM('sinh_vien', 'admin')")
    private String role;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ThongTinCaNhan thongTinCaNhan;
}
