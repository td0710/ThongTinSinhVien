package com.example.hethongthongtin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YeuCauVeXeBuytRequest {
    String sdt;
    String tuyen;
    String loaiVe;
    MultipartFile file;
}
