package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tien_ich")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TienIch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_tien_ich", length = 100, nullable = false, unique = true)
    private String tenTienIch;

}
