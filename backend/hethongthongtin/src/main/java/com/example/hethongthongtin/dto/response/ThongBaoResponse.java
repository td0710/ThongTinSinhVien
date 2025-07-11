package com.example.hethongthongtin.dto.response;

import com.example.hethongthongtin.entity.FileDinhKem;
import com.example.hethongthongtin.entity.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongBaoResponse {

    private Long id;

    private String tieuDe;

    private String noiDung;

    private String nguoiDang;

    private List<FileDinhKem> danhSachFileDinhKem;

    private LocalDateTime ngayDang;
}
