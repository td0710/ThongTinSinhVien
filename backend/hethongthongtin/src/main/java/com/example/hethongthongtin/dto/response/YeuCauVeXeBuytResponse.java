package com.example.hethongthongtin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauVeXeBuytResponse {
    private int status;
    private String message;
    private String url;
}
