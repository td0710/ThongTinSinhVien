package com.example.hethongthongtin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "giay_xac_nhan")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiayXacNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_giay", nullable = false)
    private LoaiGiay loaiGiay;

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
    }

}
