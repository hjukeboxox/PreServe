package com.example.PreServe.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
	private Long storeId;
	private String userId;
	private String userPhoneNum;
	private String reserveDate;
	private String reserveTime;
}
