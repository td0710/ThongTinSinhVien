package com.example.hethongthongtin.dto.response;

import com.example.hethongthongtin.entity.TienIch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhongResponse {

    private Long id;

    private String tenPhong;

    private String loaiPhong;

    private Integer soSv;

    private Integer soLuongDaDangKy;

    private Long gia;

    private List<TienIch> tienIchList;

}
