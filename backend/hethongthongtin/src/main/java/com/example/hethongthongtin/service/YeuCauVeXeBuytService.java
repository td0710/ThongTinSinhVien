package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.YeuCauVeXeBuytRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface YeuCauVeXeBuytService {
    void createYeuCauVeXe(YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest, MultipartFile multipartFile) throws IOException
            , GeneralSecurityException;
}
