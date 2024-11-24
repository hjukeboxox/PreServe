package com.example.PreServe.service.impl;

import com.example.PreServe.domain.dto.*;
import com.example.PreServe.domain.entity.Reservation;
import com.example.PreServe.domain.entity.Review;
import com.example.PreServe.domain.entity.Store;
import com.example.PreServe.domain.entity.User;
import com.example.PreServe.domain.repository.ReservationRepository;
import com.example.PreServe.domain.repository.ReviewRepository;
import com.example.PreServe.domain.repository.StoreRepository;
import com.example.PreServe.domain.repository.UserRepository;
import com.example.PreServe.service.AppService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    // 거리계산에 필요한 변수
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 매장을 등록 한다. 등록이 성공할경우 true 실패할경우 false를 리턴한다.
     */
    @Override
    @Transactional
    public ResponseEntity<?> registerStore(RegisterStoreDto registerStoreDto) {
        String storeAddress = registerStoreDto.getStoreAddress(); // 매장 주소
        String storeAddressDocument = searchLatLonByPartnerLoc(registerStoreDto.getStoreAddress());
        Map<String, String> storeMap = parseLatLon(storeAddressDocument);
        Store regiStoreInfo = Store.builder()
                .storeName(registerStoreDto.getStoreName())
                .storeDesc(registerStoreDto.getStoreDesc())
                .storeAddress(storeAddress)
                .userId(registerStoreDto.getUserId())
                .latitude(Double.parseDouble(storeMap.get("x")))
                .longitude(Double.parseDouble(storeMap.get("y")))
                .build();
        Store saved = storeRepository.save(regiStoreInfo);
        return ResponseEntity.ok(saved);
    }

    /**
     * 상점을 찾는다. 이름순 , 별점순 , 거리순으로 옵션을 주어 찾을 수 있다.
     *
     * @param searchRequestDto
     * @return
     */
    @Override
    public List<Store> storesSearch(SearchRequestDto searchRequestDto) {
        switch (searchRequestDto.getOrderType()) {
            case "N": // 이름순
                return storeRepository.findAll(Sort.by(searchRequestDto.getIsAscending().equals("Y") ? Sort.Order.asc("storeName") : Sort.Order.desc("storeName")));
            case "S": // 별점순
                return storeRepository.findAll(Sort.by(searchRequestDto.getIsAscending().equals("Y") ? Sort.Order.asc("storeStar") : Sort.Order.desc("storeStar")));
            case "D": // 거리순
                List<Store> stores = storeRepository.findAll();
                Optional<User> userInfo = userRepository.findByUserId(searchRequestDto.getUserId());
                User userDto = userInfo.get();
                for (Store store : stores) {
                    store.setDistance(calculateDistance(userDto.getLatitude(), userDto.getLongitude(), store.getLatitude(), store.getLongitude()));
                }
                return searchRequestDto.getIsAscending().equals("Y") ? stores.stream().sorted(Comparator.comparing(Store::getDistance)).collect(Collectors.toList()) : stores.stream().sorted(Comparator.comparing(Store::getDistance).reversed()).collect(Collectors.toList());
            default:
                return storeRepository.findAll();
        }
    }


    /**
     * 매장 예약
     *
     * @param reservationRequestDto
     * @return
     */
    @Transactional
    @Override
    public String reservationSave(ReservationRequestDto reservationRequestDto) {
        Optional<Store> store = storeRepository.findById(reservationRequestDto.getStoreId());
        Optional<User> userInfo = userRepository.findByUserId(reservationRequestDto.getUserId());

        Optional<Reservation> checkReservation = reservationRepository.findByUserIdAndReserveDateAndReservationStatus(reservationRequestDto.getUserId(), reservationRequestDto.getReserveDate(), "R");
        if (checkReservation.isPresent()) return "이미 예약이 되어있습니다.";
        if (store.isPresent()) {
            Reservation reservation = Reservation.builder()
                    .storeId(reservationRequestDto.getStoreId())
                    .userId(reservationRequestDto.getUserId())
                    .userPhoneNum(userInfo.get().getPhoneNum())
                    .reserveDate(reservationRequestDto.getReserveDate())
                    .reserveTime(reservationRequestDto.getReserveTime())
                    .reservationStatus("R")
                    .isSessionClosed("N")
                    .build();
            reservationRepository.save(reservation); // 예약 접수
            return "예약되었습니다.";
        }
        return "현재 예약을 할 수 없습니다 다음에 시도해주세요.";
    }

    @Override
    public Store storeDetailInfo(Long storeId) {
        return storeRepository.findById(storeId).get();
    }

    @Override
    public List<Reservation> getReservationList(ReservationListDto reservationListDto) {
        Optional<User> userInfo = userRepository.findByUserId(reservationListDto.getUserId());
        if (userInfo.isPresent()) {
            Optional<Store> storeInfo = storeRepository.findByUserId(reservationListDto.getUserId());
            return reservationRepository.findByStoreIdOrReservationStatus(storeInfo.get().getStoreId(), reservationListDto.getReservationStatus());
        }

        return null;
    }


    @Override
    @Transactional
    public Reservation reservationVisited(ReservationRequestDto reservationRequestDto) {
        LocalDate localDate = LocalDate.now();
        Optional<Reservation> reservation = reservationRepository.findByUserIdAndReserveDateAndReservationStatus(reservationRequestDto.getUserId(), localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")), "R");
        if (reservation.isPresent()) {
            Reservation reservationDto = reservation.get();
            String reservationDateTimeStr = reservationDto.getReserveDate() + reservationDto.getReserveTime() + "00";
            LocalDateTime reservationDateTime = LocalDateTime.parse(reservationDateTimeStr, dateTimeFormatter);
            LocalDateTime nowTime = LocalDateTime.now();
            if (Duration.between(nowTime, reservationDateTime).toMinutes() >= 10) {
                reservation.get().update("V"); // 10분전 도착
            } else {
                reservation.get().update("N"); // 10분 경과
            }
        }
        return reservation.get();
    }


    /**
     * 리뷰 할 수 있는 목록
     */
    @Override
    public List<Reservation> getReviewEligibleList(String userId) {
        return reservationRepository.findByUserIdAndReservationStatusAndIsSessionClosed(userId, "V", "Y");
    }

    /**
     * 리뷰 작성
     */
    @Transactional
    @Override
    public void reviewWrite(ReviewDto reviewDto) {
        Optional<Review> review = reviewRepository.findByReservIdAndUserId(reviewDto.getReservId(), reviewDto.getUserId());
        try {
            if (review.isPresent()) {
                review.get().update(reviewDto.getReviewStar());
            } else {
                Review reviewSave = Review.builder()
                        .reviewDateTime(LocalDateTime.now())
                        .reviewStar(reviewDto.getReviewStar())
                        .storeId(reviewDto.getStoreId())
                        .userId(reviewDto.getUserId())
                        .reservId(reviewDto.getReservId())
                        .build();
                reviewRepository.save(reviewSave);
            }
        } catch (Exception e) {
            log.error("");
        } finally {
            Double updatedStar = reviewRepository.findAverageStarByStoreId(reviewDto.getStoreId());
            Optional<Store> storeUpdate = storeRepository.findById(reviewDto.getStoreId());
            if (storeUpdate.isPresent()) storeUpdate.get().update(updatedStar);
        }

    }


    /**
     * 거리계산
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 두 지점 간의 거리 계산 (단위: 킬로미터)
        return EARTH_RADIUS_KM * c;
    }


    /**
     * api 이용 상점 addr로 x,y 값 얻고 -> User.latitude,User.longitude 에 set해주기
     */

    public String searchLatLonByPartnerLoc(String address) {
        String appkey = "KakaoAK 2ecf5febe1e05d5d376370e2b4d6c779";
        try {
            String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + strEncode(address);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", appkey);
            connection.setDoOutput(true);

            //응답결과
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    public String strEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> parseLatLon(String str) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(str);

            JSONArray documentsArray = (JSONArray) jsonObject.get("documents");
            if (documentsArray != null && !documentsArray.isEmpty()) {
                JSONObject document = (JSONObject) documentsArray.get(0);

                resultMap.put("x", (String) document.get("x"));
                resultMap.put("y", (String) document.get("y"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


}
