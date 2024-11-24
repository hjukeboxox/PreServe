package com.example.PreServe.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStoreDto {
	private String userId;
	private String storeName;
	private String storeDesc;
	private String storeAddress;
	private double latitude;
	private double longitude;
}
