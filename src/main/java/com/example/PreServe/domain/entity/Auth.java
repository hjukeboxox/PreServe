package com.example.PreServe.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Auth {


	@Data
	public static class SignIn {
		private String userId;
		private String password;

	}

	@Data
	public static class SignUp {
		private String userId;
		private String username;
		private String password;
		private String address;
		private String phoneNum;
		private UserType userType;
		private List<String> roles;

		public AuthEntity toEntity() {
			return AuthEntity.builder()
							.userId(this.getUserId())
							.username(this.getUsername())
							.password(this.getPassword())
							.address(this.getAddress())
							.phoneNum(this.getPhoneNum())
							.userType(this.getUserType())
							.roles(this.roles)
							.build();
		}

	}
}
