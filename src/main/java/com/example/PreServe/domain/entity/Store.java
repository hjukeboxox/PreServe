package com.example.PreServe.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "STORE")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;

	@Column(name="userId",length=20)
	private String userId; // 점장 아이디

	@Column(name="storeName",length=100)
	private String storeName;

	@Column(name="storeDesc",length=500)
	private String storeDesc;

	@Column(name="storeAddress",length=500)
	private String storeAddress;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "storeStar")
	private double storeStar = 0.0;

	@Transient
	@Setter
	private double distance;


	public Store(Long storeId, String userId, String storeName, String storeDesc, String storeAddress, double latitude, double longitude, int storeStar) {
		this.storeId = storeId;
		this.userId = userId;
		this.storeName = storeName;
		this.storeDesc = storeDesc;
		this.storeAddress = storeAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.storeStar = storeStar;
	}


	public void update(double storeStar){
		this.storeStar = storeStar;
	}

}
