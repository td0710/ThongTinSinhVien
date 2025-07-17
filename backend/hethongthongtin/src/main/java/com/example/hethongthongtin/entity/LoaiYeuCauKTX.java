package com.example.hethongthongtin.entity;

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

    public static LoaiVe fromLabel(String label) {
        for (LoaiVe tt : LoaiVe.values()) {
            if (tt.getLabel().equalsIgnoreCase(label)) {
                return tt;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy loại yêu cầu phù hợp với label: " + label);
    }
}
