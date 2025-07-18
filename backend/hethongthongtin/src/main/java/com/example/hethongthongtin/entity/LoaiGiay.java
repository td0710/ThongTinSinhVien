package com.example.hethongthongtin.entity;

import com.example.hethongthongtin.exception.AppException;
import com.example.hethongthongtin.exception.ErrorCode;

public enum LoaiGiay {
    UU_DAI_GIAO_DUC("Giấy xác nhận sinh viên để nhận ưu đãi giáo dục"),
    VAY_VON("Giấy xác nhận sinh viên để vay vốn tại NHCSXH địa phương"),
    CA_NHAN("Giấy xác nhận sinh viên để giải quyết các vấn đề cá nhân khác"),
    DANG_KY_XE("Giấy giới thiệu đăng ký xe máy");

    private final String label;

    LoaiGiay(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    public static LoaiGiay fromLabel(String label) {
        for (LoaiGiay loai : LoaiGiay.values()) {
            if (loai.getLabel().equals(label)) {
                return loai;
            }
        }
        throw new AppException(ErrorCode.INVALID_LOAI_GIAY);
    }
}
