package com.example.PreServe.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "REVIEW")
@Entity
public class Review {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	private Long reservId;

	@Column(name = "reviewDateTime")
	private LocalDateTime reviewDateTime;

	@Column(name = "storeId")
	private Long storeId; // 상점 정보 얻을때 사용

	@Column(name = "userId", length = 50, nullable = false)
	private String userId;

	@Column(name = "reviewStar")
	private Double reviewStar;


	@Builder
	public Review(Long reviewId, LocalDateTime reviewDateTime, Long storeId, String userId, Double reviewStar, Long reservId) {
		this.reviewId = reviewId;
		this.reviewDateTime = reviewDateTime;
		this.storeId = storeId;
		this.userId = userId;
		this.reviewStar = reviewStar;
		this.reservId = reservId;
	}


	public void update(Double reviewStar){
		this.reviewStar = reviewStar;
	}


}
