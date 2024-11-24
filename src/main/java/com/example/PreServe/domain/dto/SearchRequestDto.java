package com.example.PreServe.domain.dto;


import com.example.PreServe.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
	private String orderType; //    "N" 이름 , "S" 별점순 , "D" 거리순
	private String isAscending; // 정렬옵션 기본 Y 오름차순 N 내림차순
	private String userId; // 유저아이디 (점장[매장의 관리자],손님)
}
