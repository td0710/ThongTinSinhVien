package com.example.hethongthongtin.repository;

import com.example.hethongthongtin.entity.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ThongBaoRepository extends JpaRepository<ThongBao, Long> {

    @Query(
            value = """
            SELECT * FROM thong_bao
            WHERE (:start IS NULL OR ngay_dang >= :start)
            AND (:end IS NULL OR ngay_dang <= :end)
            AND (:tieuDe IS NULL OR tieu_de LIKE %:tieuDe%)
            ORDER BY ngay_dang DESC
            """,
            nativeQuery = true
    )
    List<ThongBao> findBySearch(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("tieuDe") String tieuDe
    );
}