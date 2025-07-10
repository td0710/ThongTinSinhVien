package com.example.hethongthongtin.service.impl;

import com.example.hethongthongtin.entity.ThongTinCaNhan;
import com.example.hethongthongtin.entity.Users;
import com.example.hethongthongtin.repository.UserRepository;
import com.example.hethongthongtin.security.JWTGenerator;
import com.example.hethongthongtin.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private JWTGenerator jwtGenerator;
    AuthServiceImpl(UserRepository userRepository, JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }


    public String login(String code) {


        WebClient webClient = WebClient.create();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", System.getenv("AZURE_CLIENT_ID"));
        formData.add("scope", "openid profile email");
        formData.add("code", code);
        formData.add("redirect_uri", System.getenv("AZURE_REDIRECT_URI"));
        formData.add("grant_type", "authorization_code");
        formData.add("client_secret", System.getenv("AZURE_CLIENT_SECRET"));

        String tokenUrl = "https://login.microsoftonline.com/" + System.getenv("AZURE_TENANT_ID") + "/oauth2/v2.0/token";
        System.out.println(formData);
        Map<String, Object> tokenResponse = webClient.post()
                .uri(tokenUrl)
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        if (tokenResponse == null || !tokenResponse.containsKey("id_token")) {
            throw new RuntimeException("Không nhận được id_token từ Microsoft");
        }

        String idToken = (String) tokenResponse.get("id_token");
        JwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(
                "https://login.microsoftonline.com/" + System.getenv("AZURE_TENANT_ID") + "/discovery/v2.0/keys"
        ).build();
        Jwt jwt = decoder.decode(idToken);

        String oid = jwt.getClaimAsString("oid");
        String email = jwt.getClaimAsString("preferred_username");
        String name = jwt.getClaimAsString("name");

        Users user = userRepository.findByEmail(email);
        if (user == null) {
            user = new Users();
            user.setMicrosoft_id(oid);
            user.setEmail(email);
            user.setFull_name(name);
            user.setRole("sinh_vien");
            user.setCreatedDate(LocalDateTime.now());


            ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan();

            thongTinCaNhan.setUser(user);
            user.setThongTinCaNhan(thongTinCaNhan);

            userRepository.save(user);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email, null, List.of(() -> "sinh_vien")
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);


        return jwtGenerator.generateToken(authentication);
    }


}
