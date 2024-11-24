package com.example.PreServe.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Getter
@NoArgsConstructor
@Table(name = "RESERVATION")
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservId;

	@Column(name = "reserveDate", length = 20)
	private String reserveDate; // 예약 요청 날짜

	@Column(name = "reserveTime", length = 20)
	private String reserveTime; // 예약 요청 시간

	@Column(name = "storeId")
	private Long storeId; // 상점 정보

	@Column(name = "userId", length = 20)
	private String userId;

	@Column(name = "userPhoneNum", length = 50)
	private String userPhoneNum;

	@Column(name = "reservationStatus", length = 1)
	private String reservationStatus;  // 예약 진행중 R, 예약시간 10분전 방문안함 N ,정상 방문완료 V

	@Column(name = "isSessionClosed", length = 1)
	private String isSessionClosed; // 해당 세션 종료 여부 'Y' ,'N' [서비스 끝남여부]


	@Builder
	public Reservation(Long reservId, String reserveDate, String reserveTime, Long storeId, String userId, String userPhoneNum, String reservationStatus,String isSessionClosed) {
		this.reservId = reservId;
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.storeId = storeId;
		this.userId = userId;
		this.userPhoneNum = userPhoneNum;
		this.reservationStatus = reservationStatus;
		this.isSessionClosed = isSessionClosed;
	}

	public void update(String reservationStatus){
		this.reservationStatus = reservationStatus;
		this.isSessionClosed = "Y";
	}
}
