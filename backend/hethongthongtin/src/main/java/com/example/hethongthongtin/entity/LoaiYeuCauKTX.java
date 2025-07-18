package com.example.hethongthongtin.entity;

import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;

public enum LoaiYeuCauKTX {
    DangKy("Đăng ký"),
    DoiPhong("Đổi phòng"),
    TraPhong("Trả phòng");

    private final String label;

    LoaiYeuCauKTX(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static LoaiYeuCauKTX fromLabel(String label) {
        for (LoaiYeuCauKTX tt : LoaiYeuCauKTX.values()) {
            if (tt.getLabel().equalsIgnoreCase(label)) {
                return tt;
            }
        }
        throw new AppException(ErrorCode.INVALID_LOAI_YEU_CAU);
    }
}
