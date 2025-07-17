package com.example.hethongthongtin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauPhongResponse {

    Long id ;

    PhongResponse phongHienTai;

    PhongResponse phongMongMuon;

    String trangThai ;

    String loaiYeuCau ;
}
