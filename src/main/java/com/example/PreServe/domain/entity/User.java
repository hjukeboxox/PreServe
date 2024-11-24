package com.example.PreServe.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@Column(name="userId",length=20) //점장의 userId, 이용자일경우 이용자의userId
	private String userId;

	@Column(name="userName",length=50)
	private String userName;   //점장의 username, 이용자일경우 username

	@Column(name="passWord",length=80,nullable = false)
	private String passWord;

	@Column(name="phoneNum",length=50)
	private String phoneNum;

	@Enumerated(EnumType.STRING)
	@Column(name="userType",length=20,nullable = false)
	private UserType userType;

	@Column(name="address",length=500,nullable = false)
	private String address;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;


	public User(Long id, String userId, String userName, String passWord, String phoneNum, UserType userType, String address, double latitude, double longitude) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNum = phoneNum;
		this.userType = userType;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void update(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.passWord = user.getPassWord();
		this.phoneNum = user.getPhoneNum();
		this.userType = user.getUserType();
		this.address = user.getAddress();
		this.latitude = user.getLatitude();
		this.longitude = user.getLongitude();
	}

}

