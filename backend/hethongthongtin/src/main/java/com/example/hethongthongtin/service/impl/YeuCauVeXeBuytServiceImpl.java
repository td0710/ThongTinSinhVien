package com.example.hethongthongtin.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.hethongthongtin.dto.request.YeuCauVeXeBuytRequest;
import com.example.hethongthongtin.dto.response.UploadResponse;
import com.example.hethongthongtin.dto.response.YeuCauVeXeBuytReponse;
import com.example.hethongthongtin.entity.LoaiVe;
import com.example.hethongthongtin.entity.ThongTinCaNhan;
import com.example.hethongthongtin.entity.TrangThai;
import com.example.hethongthongtin.entity.YeuCauVeXeBuyt;
import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;
import com.example.hethongthongtin.repository.ThongTinCaNhanRepository;
import com.example.hethongthongtin.repository.YeuCauVeXeBuytRepository;
import com.example.hethongthongtin.service.YeuCauVeXeBuytService;
import com.example.hethongthongtin.util.GoogleDriveUpload;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class YeuCauVeXeBuytServiceImpl implements YeuCauVeXeBuytService {

    private final GoogleDriveUpload googleDriveUpload;
    private final YeuCauVeXeBuytRepository yeuCauVeXeBuytRepository;
    private final ThongTinCaNhanRepository thongTinCaNhanRepository;
    private final Cloudinary cloudinary;


    YeuCauVeXeBuytServiceImpl(YeuCauVeXeBuytRepository yeuCauVeXeBuytRepository,
                              GoogleDriveUpload googleDriveUpload,
                              ThongTinCaNhanRepository thongTinCaNhanRepository,
                              Cloudinary cloudinary
                       ) {
        this.yeuCauVeXeBuytRepository = yeuCauVeXeBuytRepository;
        this.googleDriveUpload = googleDriveUpload;
        this.thongTinCaNhanRepository = thongTinCaNhanRepository;
        this.cloudinary = cloudinary;
    }


    @Override
    public void createYeuCauVeXe(YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest,
                                 MultipartFile file,
                                 Long userId) throws IOException,
            GeneralSecurityException {

        String contentType = file.getContentType();

        if (file.isEmpty() || contentType == null || !contentType.startsWith("image/")) {
            throw new AppException(ErrorCode.INVALID_FILE);
        }

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        Map<?, ?> uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.asMap(
                "folder", "vxb_img"
        ));

        String imageUrl = (String) uploadResult.get("secure_url");

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId) ;

        if(thongTinCaNhan == null || thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }


        YeuCauVeXeBuyt yeuCauVeXeBuyt = YeuCauVeXeBuyt.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .loaiVe(LoaiVe.fromLabel(yeuCauVeXeBuytRequest.getLoaiVe()))
                .tuyen(yeuCauVeXeBuytRequest.getTuyen() == null ? "Liên tuyến" : yeuCauVeXeBuytRequest.getTuyen())
                .soDienThoai(yeuCauVeXeBuytRequest.getSdt())
                .trangThai(TrangThai.DangTiepNhan)
                .duongDanAnh(imageUrl)
                .ngayYeuCau(LocalDateTime.now())
                .ghiChu("Yêu cầu mới tạo. Đang chờ xử lý.")
                .build() ;

        yeuCauVeXeBuytRepository.save(yeuCauVeXeBuyt);
    }

    @Override
    public List<YeuCauVeXeBuytReponse> getYeuCauVeXeBuytList(Long userId) {

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        if(thongTinCaNhan == null || thongTinCaNhan.getMaSinhVien().isEmpty()) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }

        List<YeuCauVeXeBuyt> yeuCauVeXeBuyt = yeuCauVeXeBuytRepository.findAllByMaSinhVien(thongTinCaNhan.getMaSinhVien());


        return yeuCauVeXeBuyt
                .stream()
                .map(vxb -> YeuCauVeXeBuytReponse.builder()
                        .id(vxb.getId())
                        .trangThai(vxb.getTrangThai().getLabel())
                        .ngayTao(vxb.getNgayYeuCau())
                        .noiNhan(vxb.getNoiNhan())
                        .ngayNhan(vxb.getNgayNhan())
                        .ghiChu(vxb.getGhiChu())
                        .tuyen(vxb.getTuyen())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteYeuCauVeXeBuyt(Long id) {

        if (!yeuCauVeXeBuytRepository.existsById(id)) {
            throw new AppException(ErrorCode.REQUEST_NOT_FOUND);
        }

        yeuCauVeXeBuytRepository.deleteById(id);
    }
}
