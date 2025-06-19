package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.request.YeuCauVeXeBuytRequest;
import com.example.hethongthongtin.dto.response.YeuCauGiayXacNhanResponse;
import com.example.hethongthongtin.dto.response.YeuCauVeXeBuytResponse;
import com.example.hethongthongtin.entity.YeuCauVeXeBuyt;
import com.example.hethongthongtin.repository.YeuCauVeXeBuytRepository;
import com.example.hethongthongtin.service.YeuCauVeXeBuytService;
import com.example.hethongthongtin.util.GoogleDriveUpload;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@Transactional
public class YeuCauVeXeBuytImpl implements YeuCauVeXeBuytService {

    private GoogleDriveUpload googleDriveUpload;
    private YeuCauVeXeBuytRepository yeuCauVeXeBuytRepository;

    YeuCauVeXeBuytImpl(YeuCauVeXeBuytRepository yeuCauVeXeBuytRepository,
                       GoogleDriveUpload googleDriveUpload) {
        this.yeuCauVeXeBuytRepository = yeuCauVeXeBuytRepository;
        this.googleDriveUpload = googleDriveUpload;
    }


    @Override
    public void createYeuCauVeXe(YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest,MultipartFile file) throws IOException,
            GeneralSecurityException {

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        YeuCauVeXeBuytResponse res = googleDriveUpload.uploadImageToDrive(tempFile);
        System.out.println(res);

    }

}
