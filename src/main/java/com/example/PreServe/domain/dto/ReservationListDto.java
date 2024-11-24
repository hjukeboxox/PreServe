package com.example.PreServe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationListDto {
	private String userId; // 앱 점장이 전용 ID
	private String reserveDate; // 예약 요청 날짜
	private String reserveTime; // 예약 요청 시간
	private String reservationStatus; //예약 상태

}
