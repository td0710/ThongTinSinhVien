package com.example.hethongthongtin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchPhongRequest {

    private String ten;

    private String loaiPhong;

    private Integer soSv;

    private Long start;

    private Long end;

    private Boolean trong;

}
