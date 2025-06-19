package com.example.hethongthongtin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DangKyXeBuytDto {
    String sdt;
    String tuyen;
    String loaiTuyen;

    MultipartFile anh ;
}
