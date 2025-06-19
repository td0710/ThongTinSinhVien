package com.example.hethongthongtin.service;

import com.example.hethongthongtin.dto.request.YeuCauVeXeBuytRequest;
import com.example.hethongthongtin.dto.response.YeuCauVeXeBuytReponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface YeuCauVeXeBuytService {
    void createYeuCauVeXe(YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest, MultipartFile multipartFile,Long userId) throws IOException
            , GeneralSecurityException;

    List<YeuCauVeXeBuytReponse> getYeuCauVeXeBuytList(Long userId) throws IOException;
    void deleteYeuCauVeXeBuyt(Long id) ;

}
