package com.example.PreServe.domain.repository;

import com.example.PreServe.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserIdAndReserveDateAndReservationStatus(String userId, String reserveDate, String reservationStatus); // 손님의 예약 확인

    List<Reservation> findByUserIdAndReservationStatusAndIsSessionClosed(String userId, String reservationStatus, String isSessionClosed);

    List<Reservation> findByStoreIdOrReservationStatus(Long storeId, String reservationStatus);


}
