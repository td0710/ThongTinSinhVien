package com.example.hethongthongtin.entity;

import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;

public enum TrangThai {
    DangTiepNhan("Đang tiếp nhận"),
    DaTiepNhan("Đã tiếp nhận"),
    HoanThanh("Hoàn thành"),
    TuChoi("Từ chối");

    private final String label;

    TrangThai(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static TrangThai fromLabel(String label) {
        for (TrangThai tt : TrangThai.values()) {
            if (tt.getLabel().equalsIgnoreCase(label)) {
                return tt;
            }
        }
        throw new AppException(ErrorCode.INVALID_TRANG_THAI);
    }
}
