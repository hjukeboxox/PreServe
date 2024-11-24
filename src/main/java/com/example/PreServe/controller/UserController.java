package com.example.PreServe.controller;

import com.example.PreServe.common.AuthService;
import com.example.PreServe.domain.entity.Auth;

import com.example.PreServe.domain.entity.Store;
import com.example.PreServe.domain.entity.User;
import com.example.PreServe.domain.entity.UserType;
import com.example.PreServe.domain.repository.AuthEntityRepository;
import com.example.PreServe.domain.repository.StoreRepository;
import com.example.PreServe.domain.repository.UserRepository;
import com.example.PreServe.security.TokenProvider;
import com.example.PreServe.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final AppService appService;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final AuthEntityRepository authEntityRepository;

    /**
     * 파트너 가입 기능.
     */
    @PostMapping("/signup/partner")
    public ResponseEntity<?> signUpPartner(@RequestBody Auth.SignUp request) {
        var result = this.authService.register(request);

        //유저 저장
        String document = appService.searchLatLonByPartnerLoc(result.getAddress());
        Map<String, String> resultMap = appService.parseLatLon(document);
        User userSave = User.builder()
                .userId(result.getUserId())
                .userName(result.getUsername())
                .passWord(result.getPassword())
                .address(result.getAddress())
                .phoneNum(result.getPhoneNum())
                .latitude(Double.parseDouble(resultMap.get("x")))
                .longitude(Double.parseDouble(resultMap.get("y")))
                .userType(request.getUserType())
                .build();
        User saved = userRepository.save(userSave);

        return ResponseEntity.ok(saved);

    }

    /**
     * 매장이용자 가입기능.
     */
    @PostMapping("/signup/customer")
    public ResponseEntity<?> signUpCustomer(@RequestBody Auth.SignUp request) {
        var result = this.authService.register(request);

        //유저 저장
        String document = appService.searchLatLonByPartnerLoc(result.getAddress());
        Map<String, String> resultMap = appService.parseLatLon(document);
        User userSave = User.builder()
                .userId(result.getUserId())
                .userName(result.getUsername())
                .passWord(result.getPassword())
                .address(result.getAddress())
                .phoneNum(result.getPhoneNum())
                .latitude(Double.parseDouble(resultMap.get("x")))
                .longitude(Double.parseDouble(resultMap.get("y")))
                .userType(request.getUserType())
                .build();
        User saved = userRepository.save(userSave);
        return ResponseEntity.ok(saved);
    }




    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {
        //로그인용 API
        //1. 유저일치 검증
        var userAuth = this.authService.authenticate(request);
        //어떤사용자가 로그인했는지 기록
        //log.info("user login -> " + request.getUserId());
        //2. 토큰생성하여 반환하기
        var token = this.tokenProvider.generateToken(userAuth.getUserId(), userAuth.getRoles());
        return ResponseEntity.ok(token);
    }


}
