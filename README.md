# HanghaeBnb-BE
<br/><br/> 

## 🔗 STEP1. 프로젝트 소개

---

### Airbnb 클론코딩 프로젝트

#### https://www.airbnb.co.kr/
<br/><br/>

##  🛠️ STEP2. 프로젝트 기능 명세서

 #### 1. 회원 가입 API

    • email, username, password를 Client에서 전달받기

    • username(중복 확인 필요 : BE)
    
    • password (비밀번호 확인 및 비밀번호 체크 : FE)

    • E-mail : (이메일 형식 체크,FE, 중복 확인 필요,BE)

    • birth (생년월일 8자 형태체크 (ex 1995-03-06 )
      


  #### 2. 로그인 API

    • 일반 로그인 :username, password를 Client에서 전달받기
    
    • 로그인 시 username 노출, 토큰 쿠키 저장, 로그아웃 시 토큰 헤더에서 제거
    
    • AceessToken, RefreshToken
      
    • E-mail : (회원 등록여부 체크)

    • password (비밀번호 확인)

    • 카카오톡 로그인 : 추후 추가

      
  #### 3. 전체 페이지 (게시글 조회, 검색) API 

    • 내림 차순 정렬 : 등록 날짜 
    
    • 카테고리 필터링
    
    • 검색하기( 추후 구현)
    
    • 게시글 클릭 시 : 상세 페이지로 이동
    
    • 찜 목록 버튼 - [로그인 유저 만 가능]
    
    • 무한 스크롤 : 추후 추가 [페이지네이션]
   
    
    
 #### 4. 상세 페이지 API

    • 기본 정렬 : 최신 날짜 순으로 정렬
    
    • 찜 목록 버튼 - [로그인 유저 만 가능]
    
    • 검색하기( 추후 구현)
    
    • 예약하기 - [로그인 유저 만 가능]
    

 
  #### 5. 마이페이지 (호스트 글쓰기, 위시리스트, 리뷰) API
  
    • 호스트 글쓰기 - [로그인 유저 만 가능]
    
    • 위시리스트  - [로그인 유저 만 가능]
    
    • 리뷰달기  - [로그인 유저 + 예약한 유저]
    
    • 회원정보수정(추후)
<br/><br/>


## 🧱 STEP 3 :  ERD
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FGn3PU%2Fbtr2FiqCdkS%2FDBQH8LKlmGdDl2gextdNVK%2Fimg.png"  width="1000" height="800">
<br/><br/> 

## 🏗️ STEP 4 :   API Specification
API 명세서 보러가기 : https://www.notion.so/bf9c53323687464d817a725dd503b810?v=0b366ade4cc646799754b9dcc6c95d81
<br/><br/> <br/> 


 ## 💡 STEP 5 : Core Tools
<img src="https://img.shields.io/badge/Spring-green?style=for-the-badge&logo=Spring&logoColor=#6DB33F"> <img src="https://img.shields.io/badge/Spring Boot-green?style=for-the-badge&logo=Spring Boot&logoColor=#6DB33F"> <img src="https://img.shields.io/badge/Spring Security-green?style=for-the-badge&logo=Spring Security&logoColor=#6DB33F">
<br/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>  <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"/>
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white">

<br/><br/>
 
 
   ## 📌 STEP 6 : Trouble Shooting
| 트러블 슈팅 내용 | 해결 방법 |
| --- | --- |
| access토큰이 만료되었을시 자동으로 refresh 토큰값의 유효성을 검사하고,<br/> 검증이 되면헤더에 access토큰값을 새로 발급해 전달하는 로직을 짜는 중 LazyInitializationException에러가 발생 | 1. Hibernate.initialize(refresh.getUser())메서드로 프록시 객체에 하위 엔티티를 초기화 <br/>2. RefreshToken 엔티티를 Eager타입으로 변경 |
| ERD wishList, House, User 간의 매핑 수정   | WishList - house 간의 중간 테이블의 필요성에 대한 고민.<br/>  wishList로 통합해서 사용 |
  

<br/><br/>
 

 
## 🗒 STEP 7 : 개선을 위한 고려사항
  | 개선 가능한 부분 | 의논한 개선 방안 |
  | --- | --- |
  |  |  |
  |  |  |



<br/><br/>

## 👨‍👨‍👧 STEP 8 : 팀원 정보 및 팀 노션

| 이름 | 깃허브 주소 |
| --- | --- |
| 박재용 | https://github.com/jyparkDev |
| 오세영 | https://github.com/osy9536 |
| 김여원 | https://github.com/YeowonKIM |

#### TEAM NOTION : https://www.notion.so/2-de44a511f02a43dc8a4ad23baccac897
