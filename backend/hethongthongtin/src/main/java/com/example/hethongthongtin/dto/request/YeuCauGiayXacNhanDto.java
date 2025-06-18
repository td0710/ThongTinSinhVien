package com.example.hethongthongtin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauGiayXacNhanDto {
    private Long user_id;
    private String loaiGiay;
}
