package com.example.PreServe.domain.entity;

public enum UserType {
	MANAGER("M"),
	PARTNER("P"),
	CUSTOMER("C");
	private String postion;
	UserType(String postion) {
		this.postion = postion;
	}
}
