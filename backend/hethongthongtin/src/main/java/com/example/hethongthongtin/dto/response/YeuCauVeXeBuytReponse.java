package com.example.hethongthongtin.dto.response;

import com.example.hethongthongtin.entity.TrangThai;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauVeXeBuytReponse {

    private Long id ;

    private LocalDateTime ngayTao;

    private String tuyen;

    private String trangThai;

    private String ghiChu;

    private LocalDate ngayNhan;

    private String noiNhan;

}
