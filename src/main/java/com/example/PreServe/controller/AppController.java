package com.example.PreServe.controller;

import com.example.PreServe.domain.dto.*;
import com.example.PreServe.domain.entity.Reservation;
import com.example.PreServe.domain.entity.Store;
import com.example.PreServe.domain.entity.User;
import com.example.PreServe.domain.entity.UserType;
import com.example.PreServe.domain.repository.UserRepository;
import com.example.PreServe.service.AppService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/app")
public class AppController {


    @Autowired
    private AppService appService;
    private UserRepository userRepository;

    /**
     * 점장이 파트너매장 정보를 등록
     */
    @PostMapping("/stores/register")
    public ResponseEntity<?> registerStore(@RequestBody RegisterStoreDto registerStoreDto) {
        return appService.registerStore(registerStoreDto);
    }

    /**
     * 매장검색 기능
     */
    @PostMapping("/stores/search")
    public ResponseEntity<?> storesSearch(@RequestBody SearchRequestDto searchRequestDto) {
        return ResponseEntity.ok(appService.storesSearch(searchRequestDto));
    }

    /**
     * 매장 상세 정보
     */
    @GetMapping("/stores/storeDetailInfo/{storeId}")
    public Store storeDetailInfo(@PathVariable Long storeId) {
        return appService.storeDetailInfo(storeId);
    }


    /**
     * 매장예약 기능
     */
    @PostMapping("/stores/reservation")
    public String reservationSave(@RequestBody ReservationRequestDto reservationRequestDto) {
        if (null == reservationRequestDto.getUserId() || reservationRequestDto.getUserId().equals(""))
            return "회원가입을 진행해주세요.";
        return appService.reservationSave(reservationRequestDto);
    }

    /**
     * 점주가 자신의 매장의 예약리스트를 확인
     */
    @PostMapping("/stores/reservationList")
    public List<Reservation> reservationSave(@RequestBody ReservationListDto reservationListDto) {
        return appService.getReservationList(reservationListDto);
    }

    /**
     * 고객이 예약한 이후 키오스크를 통해 방문 확인 정보 전달
     */
    @PostMapping("/stores/reservationVisited")
    public ResponseEntity<?> reservationVisited(@RequestBody ReservationRequestDto reservationRequestDto) {
        return ResponseEntity.ok(appService.reservationVisited(reservationRequestDto));
    }


    /**
     * 고객이 리뷰 가능한 리스트
     *
     * @param userId
     */
    @GetMapping("/stores/reviewEligibles/{userId}")
    public List<Reservation> getReviewEligibleList(@PathVariable String userId) {
        return appService.getReviewEligibleList(userId);
    }

    /**
     * 예약 완료 후 사용 이후에 리뷰를 작성하는 기능
     */
    @PostMapping("/stores/reviewWrite")
    public void reviewWrite(@RequestBody ReviewDto reviewDto) {
        appService.reviewWrite(reviewDto);
    }

}
