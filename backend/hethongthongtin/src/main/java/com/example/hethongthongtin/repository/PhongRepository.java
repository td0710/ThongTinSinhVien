package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.Phong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Long> {

    Page<Phong> findAll(Pageable pageable);


    @Query(
            value = """
        SELECT * FROM phong
        WHERE (:start IS NULL OR gia >= :start)
        AND (:end IS NULL OR gia <= :end)
        AND (:loaiPhong IS NULL OR loai_phong = :loaiPhong)
        AND (:ten IS NULL OR ten_phong LIKE CONCAT('%', :ten, '%'))
        AND (:soSv IS NULL OR so_sv = :soSv)
        AND ((:trong = true AND so_luong_da_dang_ky < so_sv)
            OR (:trong = false)
        )        """,
            countQuery = """
        SELECT COUNT(*) FROM phong
        WHERE (:start IS NULL OR gia >= :start)
        AND (:end IS NULL OR gia <= :end)
        AND (:loaiPhong IS NULL OR loai_phong = :loaiPhong)
        AND (:ten IS NULL OR ten_phong LIKE CONCAT('%', :ten, '%'))
        AND (:soSv IS NULL OR so_sv = :soSv)
        AND (:trong = false OR so_luong_da_dang_ky < so_sv)
        """,
            nativeQuery = true
    )
    Page<Phong> findAllBySearch(Pageable pageable,
                                @Param("ten") String ten,
                                @Param("loaiPhong") String loaiPhong,
                                @Param("soSv") Integer soSv,
                                @Param("start") Long start,
                                @Param("end") Long end,
                                @Param("trong") Boolean trong
    );
}
