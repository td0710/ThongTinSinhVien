package com.example.hethongthongtin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchThongBaoRequest {

    private String tieuDe ;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
