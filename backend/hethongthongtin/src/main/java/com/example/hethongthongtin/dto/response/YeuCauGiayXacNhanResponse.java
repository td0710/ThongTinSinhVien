package com.example.hethongthongtin.dto.response;

import com.example.hethongthongtin.entity.LoaiGiay;
import com.example.hethongthongtin.entity.TrangThai;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YeuCauGiayXacNhanResponse {
    private String loaiGiay;

    private LocalDateTime ngayTao;

    private String trangThai;

    private LocalDate ngayNhan;

    private String noiNhan;

    private String ghiChu;
}
