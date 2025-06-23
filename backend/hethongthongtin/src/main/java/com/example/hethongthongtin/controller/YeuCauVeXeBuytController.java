package com.example.hethongthongtin.controller;

import com.example.hethongthongtin.dto.request.YeuCauVeXeBuytRequest;
import com.example.hethongthongtin.dto.response.YeuCauVeXeBuytReponse;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.YeuCauVeXeBuytService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/secure/yeucauvexebuyt")
public class YeuCauVeXeBuytController {

    private final YeuCauVeXeBuytService yeuCauVeXeBuytService;
    private final JWTGenerator jwtGenerator;
    YeuCauVeXeBuytController(YeuCauVeXeBuytService yeuCauVeXeBuytService,
                             JWTGenerator jwtGenerator
                             ) {
        this.yeuCauVeXeBuytService = yeuCauVeXeBuytService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute YeuCauVeXeBuytRequest yeuCauVeXeBuytRequest,
                                    @CookieValue("jwt") String token
                                    ) throws IOException, GeneralSecurityException {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);
        yeuCauVeXeBuytService.createYeuCauVeXe(yeuCauVeXeBuytRequest,yeuCauVeXeBuytRequest.getFile(),userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<YeuCauVeXeBuytReponse>> getAll(@CookieValue("jwt") String token)
            throws IOException {
        Long userId = jwtGenerator.extractUserIdFromJwt(token);
        return ResponseEntity.ok(yeuCauVeXeBuytService.getYeuCauVeXeBuytList(userId));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id)  {
        yeuCauVeXeBuytService.deleteYeuCauVeXeBuyt(id);
        return ResponseEntity.ok("Hủy yêu cầu thành công") ;
    }
}
