package com.example.PreServe.service;

import com.example.PreServe.domain.dto.*;
import com.example.PreServe.domain.entity.Reservation;
import com.example.PreServe.domain.entity.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface AppService {

    ResponseEntity<?> registerStore(RegisterStoreDto registerStoreDto);

    List<Store> storesSearch(SearchRequestDto searchRequestDto);

    String searchLatLonByPartnerLoc(String address);

    Map<String, String> parseLatLon(String dataToParse);

    String reservationSave(ReservationRequestDto reservationRequestDto);

    Store storeDetailInfo(Long storeId);

    List<Reservation> getReservationList(ReservationListDto reservationListDto);

    Reservation reservationVisited(ReservationRequestDto reservationRequestDto);

    void reviewWrite(ReviewDto reviewDto);

    List<Reservation> getReviewEligibleList(String userId);

}
