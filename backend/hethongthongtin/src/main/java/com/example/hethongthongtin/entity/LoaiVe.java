package com.example.hethongthongtin.entity;

import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;

public enum LoaiVe {
    mot_tuyen("motTuyen"),
    lien_tuyen("lienTuyen");

    private final String label;

    LoaiVe(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static LoaiVe fromLabel(String label) {
        for (LoaiVe tt : LoaiVe.values()) {
            if (tt.getLabel().equalsIgnoreCase(label)) {
                return tt;
            }
        }
        throw new AppException(ErrorCode.INVALID_LOAI_VE);
    }
}
