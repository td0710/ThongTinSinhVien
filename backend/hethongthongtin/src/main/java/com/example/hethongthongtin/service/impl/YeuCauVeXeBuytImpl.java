package com.example.hethongthongtin.service.impl;

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
import com.example.hethongthongtin.repository.UserRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class YeuCauVeXeBuytImpl implements YeuCauVeXeBuytService {

    private GoogleDriveUpload googleDriveUpload;
    private YeuCauVeXeBuytRepository yeuCauVeXeBuytRepository;
    private ThongTinCaNhanRepository thongTinCaNhanRepository;
    private UserRepository userRepository;


    YeuCauVeXeBuytImpl(YeuCauVeXeBuytRepository yeuCauVeXeBuytRepository,
                       GoogleDriveUpload googleDriveUpload,
                       ThongTinCaNhanRepository thongTinCaNhanRepository,
                       UserRepository userRepository
                       ) {
        this.yeuCauVeXeBuytRepository = yeuCauVeXeBuytRepository;
        this.googleDriveUpload = googleDriveUpload;
        this.thongTinCaNhanRepository = thongTinCaNhanRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void createYeuCauVeXe(YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest,MultipartFile file,Long userId) throws IOException,
            GeneralSecurityException {

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        UploadResponse res = googleDriveUpload.uploadImageToDrive(tempFile);
        System.out.println(res);

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId) ;

        if(thongTinCaNhan == null || thongTinCaNhan.getMaSinhVien().equals("")) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND) ;
        }
        YeuCauVeXeBuyt yeuCauVeXeBuyt = YeuCauVeXeBuyt.builder()
                .maSinhVien(thongTinCaNhan.getMaSinhVien())
                .loaiVe(LoaiVe.fromLabel(yeuCauVeXeBuytRequest.getLoaiVe()))
                .tuyen(yeuCauVeXeBuytRequest.getTuyen().equals("") ? "Liên tuyến" : yeuCauVeXeBuytRequest.getTuyen())
                .soDienThoai(yeuCauVeXeBuytRequest.getSdt())
                .trangThai(TrangThai.DangTiepNhan)
                .duongDanAnh(res.getUrl())
                .ngayYeuCau(LocalDateTime.now())
                .ghiChu("Yêu cầu mới tạo. Đang chờ xử lý.")
                .build() ;

        yeuCauVeXeBuytRepository.save(yeuCauVeXeBuyt);
    }

    @Override
    public List<YeuCauVeXeBuytReponse> getYeuCauVeXeBuytList(Long userId) throws IOException {

        ThongTinCaNhan thongTinCaNhan = thongTinCaNhanRepository.findByUserId(userId);

        List<YeuCauVeXeBuyt> yeuCauVeXeBuyt = yeuCauVeXeBuytRepository.findAllByMaSinhVien(thongTinCaNhan.getMaSinhVien());

        List<YeuCauVeXeBuytReponse> yeuCauVeXeBuytReponseList = StreamSupport
                .stream(yeuCauVeXeBuyt.spliterator(),false)
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
        return yeuCauVeXeBuytReponseList;
    }

    @Override
    public void deleteYeuCauVeXeBuyt(Long id) {
        yeuCauVeXeBuytRepository.deleteById(id);
    }
}
