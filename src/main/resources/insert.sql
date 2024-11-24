
-- CUSTOMER
INSERT INTO USER (userId, userName, passWord, phoneNum, userType, address, latitude, longitude)
VALUES
    ('user01', '김민지', 'abcd1234', '01011122334', 'CUSTOMER', '서울 구로구 오류동 101-5', 37.4830, 126.8345),
    ('user02', '이준호', 'abcd1234', '01011223345', 'CUSTOMER', '서울 구로구 오류동 102-8', 37.4835, 126.8348),
    ('user03', '박지윤', 'abcd1234', '01012334456', 'CUSTOMER', '서울 구로구 오류동 103-12', 37.4840, 126.8350),
    ('user04', '최영훈', 'abcd1234', '01013445567', 'CUSTOMER', '서울 구로구 오류동 104-14', 37.4845, 126.8352),
    ('user05', '정혜원', 'abcd1234', '01014556678', 'CUSTOMER', '서울 구로구 오류동 105-18', 37.4850, 126.8355),
    ('user06', '강민수', 'abcd1234', '01015667789', 'CUSTOMER', '서울 구로구 오류동 106-20', 37.4855, 126.8358);


-- MANAGER
INSERT INTO USER (userId, userName, passWord, phoneNum, userType, address, latitude, longitude)
VALUES
    ('kimseongho', '김성호', 'password123', '01011112222', 'MANAGER', '서울 구로구 오류동 100', 37.4875, 126.8350),
    ('leejihoon', '이지훈', 'password123', '01012345678', 'MANAGER', '서울 구로구 오류동 101', 37.4877, 126.8352),
    ('parkminji', '박민지', 'password123', '01013456789', 'MANAGER', '서울 구로구 오류동 102', 37.4879, 126.8354),
    ('choijiwon', '최지원', 'password123', '01014567890', 'MANAGER', '서울 구로구 오류동 103', 37.4881, 126.8356),
    ('jangminseo', '장민서', 'password123', '01015678901', 'MANAGER', '서울 구로구 오류동 104', 37.4883, 126.8358),
    ('yoonseojin', '윤서진', 'password123', '01016789012', 'MANAGER', '서울 구로구 오류동 105', 37.4885, 126.8360),
    ('hanjiwon', '한지원', 'password123', '01017890123', 'MANAGER', '서울 구로구 오류동 106', 37.4887, 126.8362),
    ('kimsoojung', '김수정', 'password123', '01018901234', 'MANAGER', '서울 구로구 오류동 107', 37.4889, 126.8364),
    ('leeyanji', '이연지', 'password123', '01019012345', 'MANAGER', '서울 구로구 오류동 108', 37.4891, 126.8366),
    ('choijongseo', '최종서', 'password123', '01020123456', 'MANAGER', '서울 구로구 오류동 109', 37.4893, 126.8368),
    ('limyeji', '임예지', 'password123', '01021234567', 'MANAGER', '서울 구로구 오류동 110', 37.4895, 126.8370),
    ('kimminseok', '김민석', 'password123', '01022345678', 'MANAGER', '서울 구로구 오류동 111', 37.4897, 126.8372),
    ('janghyeri', '장혜리', 'password123', '01023456789', 'MANAGER', '서울 구로구 오류동 112', 37.4899, 126.8374),
    ('imseungho', '임승호', 'password123', '01024567890', 'MANAGER', '서울 구로구 오류동 113', 37.4901, 126.8376),
    ('leewonbin', '이원빈', 'password123', '01025678901', 'MANAGER', '서울 구로구 오류동 114', 37.4903, 126.8378),
    ('choihyojin', '최효진', 'password123', '01026789012', 'MANAGER', '서울 구로구 오류동 115', 37.4905, 126.8380),
    ('songjihoon', '송지훈', 'password123', '01027890123', 'MANAGER', '서울 구로구 오류동 116', 37.4907, 126.8382),
    ('kangjiyoung', '강지영', 'password123', '01028901234', 'MANAGER', '서울 구로구 오류동 117', 37.4909, 126.8384),
    ('choijiwon', '최지원', 'password123', '01029012345', 'MANAGER', '서울 구로구 오류동 118', 37.4911, 126.8386),
    ('leehyunjoo', '이현주', 'password123', '01030123456', 'MANAGER', '서울 구로구 오류동 119', 37.4913, 126.8388);

-- STORES
INSERT INTO STORE (userId, storeName, storeDesc, storeAddress, latitude, longitude, storeStar)
VALUES
    ('kimseongho', '오류한식', '정통 한식 맛집', '서울 구로구 오류동 100', 126.8350, 37.4875, 4),
    ('leejihoon', '참숯구이', '맛있는 숯불구이 전문점', '서울 구로구 오류동 101', 126.8352, 37.4877, 5),
    ('parkminji', '동네분식', '가정식 느낌의 분식집', '서울 구로구 오류동 102', 126.8354, 37.4879, 3),
    ('choijiwon', '피자나라', '맛있는 피자와 파스타', '서울 구로구 오류동 103', 126.8356, 37.4881, 4),
    ('jangminseo', '카페로이', '편안한 분위기의 카페', '서울 구로구 오류동 104', 126.8358, 37.4883, 4),
    ('yoonseojin', '불고기명가', '고소하고 달콤한 불고기', '서울 구로구 오류동 105', 126.8360, 37.4885, 4),
    ('hanjiwon', '삼겹살집', '신선한 삼겹살과 고기', '서울 구로구 오류동 106', 126.8362, 37.4887, 5),
    ('kimsoojung', '빵집토스트', '갓 구운 빵과 다양한 토스트', '서울 구로구 오류동 107', 126.8364, 37.4889, 4),
    ('leeyanji', '오류떡볶이', '매운 떡볶이 전문점', '서울 구로구 오류동 108', 126.8366, 37.4891, 3),
    ('choijongseo', '햄버거플러스', '수제 햄버거 전문점', '서울 구로구 오류동 109', 126.8368, 37.4893, 4),
    ('limyeji', '국밥가든', '정갈한 국밥과 한정식', '서울 구로구 오류동 110', 126.8370, 37.4895, 5),
    ('kimminseok', '오류양꼬치', '매운 양꼬치와 술안주', '서울 구로구 오류동 111', 126.8372, 37.4897, 3),
    ('janghyeri', '스시천국', '싱싱한 초밥과 해산물', '서울 구로구 오류동 112', 126.8374, 37.4899, 5),
    ('imseungho', '국수연가', '깔끔한 국수와 덮밥', '서울 구로구 오류동 113', 126.8376, 37.4901, 4),
    ('leewonbin', '오류반찬가게', '정성 가득한 반찬집', '서울 구로구 오류동 114', 126.8378, 37.4903, 4),
    ('choihyojin', '찜닭맛집', '매운 찜닭이 유명한 집', '서울 구로구 오류동 115', 126.8380, 37.4905, 4),
    ('songjihoon', '샤브샤브나라', '신선한 샤브샤브와 샐러드바', '서울 구로구 오류동 116', 126.8382, 37.4907, 5),
    ('kangjiyoung', '커리하우스', '다양한 커리와 일본식 덮밥', '서울 구로구 오류동 117', 126.8384, 37.4909, 4),
    ('choijiwon', '청국장마을', '매일 신선한 청국장', '서울 구로구 오류동 118', 126.8386, 37.4911, 3),
    ('leehyunjoo', '떡집마을', '다양한 종류의 떡을 맛볼 수 있는 곳', '서울 구로구 오류동 119', 126.8388, 37.4913, 4);