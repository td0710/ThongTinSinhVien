package com.example.hethongthongtin.entity;

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
        throw new IllegalArgumentException("Không tìm thấy loại vé phù hợp với label: " + label);
    }
}
