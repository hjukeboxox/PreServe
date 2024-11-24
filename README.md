# PreServe_hj

점주 가입 POST
http://localhost:8080/auth/signup/partner

{
  "userId": "lim123",
  "username": "hj",
  "password": "password123",
  "address":"서울특별시 구로구 천왕로 21",
  "phoneNum":"01012345607",
  "userType": "PARTNER"
  "roles": ["ROLE_READ"]
}

 
손님 가입 POST
http://localhost:8080/auth/signup/customer

{
  "userId": "hong12",
  "username": "홍길동",
  "password": "password12",
  "address":"서울특별시 구로구 천왕로 21",
  "phoneNum":"01012341111",
  "userType": "CUSTOMER",
  "roles": ["ROLE_READ"]
}

로그인 POST [아직 시큐리티 적용못함함]
http://localhost:8080/auth/signin

{
    "userId": "lim123",
    "password": "password123"
}



매장 등록 POST
http://localhost:8080/app/stores/register

{
  "userId": "lim123",
  "storeName": "맛있는 파스타",
  "storeDesc":"생면 파스타를 만드는 맛집",
  "storeAddress": "서울특별시 구로구 천왕로 56"
}


매장 검색 POST
http://localhost:8080/app/stores/search

  //  orderType   "N" 이름 , "S" 별점순 , "D" 거리순 
  //  isAscending "Y" 오름차순 "N" 내림차순
{
	 "orderType":"N",
	 "isAscending":"Y",
	 "userId":"hong12"
}

매장상세정보 GET
http://localhost:8080/app/stores/storeDetailInfo/12


매장예약 POST
http://localhost:8080/app/stores/reservation
{
	 "storeId":12,
	 "userId":"hong12",
	 "reserveDate":"20241124",
	 "reserveTime":"1900"	 
}


점주가 예약리스트 확인 POST
http://localhost:8080/app/stores/reservationList
{
	 "userId":"kimminseok" 
}



고객이 예약한 이후 키오스크를 통해 방문 확인 정보 전달 POST
http://localhost:8080/app/stores/reservationVisited

{
	 "userId":"hong12" 
}



고객이 리뷰 가능한 리스트 GET
http://localhost:8080/app/stores/reviewEligibles/hong12



예약 완료 후 사용 이후에 리뷰를 작성하는 기능 POST
http://localhost:8080/app/stores/reviewWrite
{
	"reservId":3,
	"storeId":12,
	"userId":"hong12", 
	"reviewStar":4.2
}



======== 다음주 안으로 추가로 보완 내용 ========
- 익셉션 통합 (보완예정)
- 시큐리티 세부 적용 (셀프공부 → 보완예정)
- 로그 및 스웨그 적용, 스케쥴러 적용 (보완예정)

