package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.dto.response.GiayXacNhanResponse;
import com.example.hethongthongtin.entity.GiayXacNhan;
import com.example.hethongthongtin.repository.GiayXacNhanRepository;
import com.example.hethongthongtin.service.GiayXacNhanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class GiayXacNhanImpl implements GiayXacNhanService {

    private GiayXacNhanRepository giayXacNhanRepository;

    GiayXacNhanImpl(GiayXacNhanRepository giayXacNhanRepository) {
        this.giayXacNhanRepository = giayXacNhanRepository;
    }

    public List<GiayXacNhanResponse> getAll() {
        List<GiayXacNhan> giayXacNhan = giayXacNhanRepository.findAll() ;
        List<GiayXacNhanResponse> giayXacNhanResponses = giayXacNhan
                .stream()
                .map(gxn -> GiayXacNhanResponse.builder()
                        .id(gxn.getId())
                        .name(gxn.getLoaiGiay().getLabel())
                        .build())
                .collect(Collectors.toList());
        return giayXacNhanResponses;
    }
}
