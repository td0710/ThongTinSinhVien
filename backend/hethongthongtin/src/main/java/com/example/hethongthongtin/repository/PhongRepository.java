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


    @Query(value = """
        SELECT p.* FROM phong p
        WHERE (:ten IS NULL OR p.ten_phong LIKE CONCAT('%', :ten, '%'))
          AND (:loaiPhong IS NULL OR p.loai_phong = :loaiPhong)
          AND (:soSv IS NULL OR p.so_sv = :soSv)
          AND (:start IS NULL OR p.gia >= :start)
          AND (:end IS NULL OR p.gia <= :end)
          AND (
              :trong IS NULL 
              OR (:trong = true AND (
                  SELECT COUNT(*) FROM phong_sinh_vien ps 
                  WHERE ps.phong_id = p.id 
                  AND ps.trang_thai != 'DA_CHUYEN'
              ) < p.so_sv)
              OR (:trong = false)
          )
        """,
            countQuery = """
        SELECT COUNT(*) FROM phong p
        WHERE (:ten IS NULL OR p.ten_phong LIKE CONCAT('%', :ten, '%'))
          AND (:loaiPhong IS NULL OR p.loai_phong = :loaiPhong)
          AND (:soSv IS NULL OR p.so_sv = :soSv)
          AND (:start IS NULL OR p.gia >= :start)
          AND (:end IS NULL OR p.gia <= :end)
          AND (
              :trong IS NULL 
              OR (:trong = true AND (
                  SELECT COUNT(*) FROM phong_sinh_vien ps 
                  WHERE ps.phong_id = p.id 
                  AND ps.trang_thai != 'DA_CHUYEN'
              ) < p.so_sv)
              OR (:trong = false AND 1=1)
          )
        """,
            nativeQuery = true)
    Page<Phong> findAllBySearch(Pageable pageable,
                                @Param("ten") String ten,
                                @Param("loaiPhong") String loaiPhong,
                                @Param("soSv") Integer soSv,
                                @Param("start") Long start,
                                @Param("end") Long end,
                                @Param("trong") Boolean trong);
}
