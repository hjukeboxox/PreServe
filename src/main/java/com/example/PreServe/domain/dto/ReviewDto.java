package com.example.PreServe.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
	private Long reservId;//예약Id
	private Long storeId;
	private String userId;
	private double reviewStar;
}
