### 📄개요
**********************
* **프로젝트 이름** : RoomRoomBnB
* **프로젝트 제작기간** : 2024.02.07 ~ 2024.02.14
* **프로젝트 설명** : 
   RoomRoomBnB는 숙박업소를 태그하여 사용자들이 별점을 매기고 자신의 경험을 공유할 수 있는 서비스입니다.   
  사용자는 자신이 방문한 숙소에 대한 리뷰를 작성하고, 다른 사용자의 포스트를 통해 실제 숙박 경험을 바탕으로 한 솔직한 평가를 확인할 수 있습니다. 이 플랫폼은 여행자들에게 정보를 제공하고, 숙박업소에 대한 투명한 평가를 가능하게 함으로써 더 나은 여행 계획을 세울 수 있도록 돕습니다.
###  개발환경
*********************

- <img src="https://img.shields.io/badge/Framework-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/3.18-515151?style=for-the-badge">
- <img src="https://img.shields.io/badge/Build-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/8.5-515151?style=for-the-badge">
- <img src="https://img.shields.io/badge/Language-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=for-the-badge">
- <img src="https://img.shields.io/badge/Project Encoding-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/UTF 8-EA2328?style=for-the-badge">
- <img src="https://img.shields.io/badge/DataBase-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/8.3-515151?style=for-the-badge">
- <img src="https://img.shields.io/badge/Passing-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white"/>
- <img src="https://img.shields.io/badge/Security-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
- <img src="https://img.shields.io/badge/OpenAPI-%23121011?style=for-the-badge">
![image](https://github.com/RoomRoomBnB/roombnb/assets/103111681/49bf5b9f-32cf-4c54-99f3-6d154279aae6)

###  ⚙기능구현
*********************
- [x]  **사용자 인증 기능**
- [x]  **프로필 관리**
- [x]  **게시물 CRUD 기능**
- [x]  **뉴스피드 기능**
- [x]  **댓글 CRUD 기능**
- [x]  **북마크 기능**
- [x]  **외부 API 조회 기능**

### 👩🏼‍🤝‍👩🏼멤버 구성
**************
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/pie0902"><img src="https://github.com/RoomRoomBnB/roombnb/assets/103111681/37ff3cdb-bd2f-4deb-8152-d4dec7e90d00" width="100px;" alt="윤종일"/><br /><sub><b> 윤종일 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/RebbitK"><img src="https://avatars.githubusercontent.com/u/154823447?v=4" width="100px;" alt="김형우"/><br /><sub><b> 김형우 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/yejin0901"><img src="https://avatars.githubusercontent.com/u/61917664?v=4" width="100px;" alt="김예진"/><br /><sub><b> 김예진 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/noeyodeel"><img src="https://github.com/RoomRoomBnB/roombnb/assets/103111681/506242f6-eb07-4c28-9f00-caaaa3ed42dd" width="100px;" alt="이도연"/><br /><sub><b> 이도연 </b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

* 윤종일 - 회원가입, 로그인
* 김형우 - 게시글 작성, 조회, 선택 조회, 별점순, 최신순 조회, 삭제 / API를 활용한 숙소 정보 검색/조회 기능
* 이도연 - 댓글 작성, 조회, 수정, 삭제 / Swagger 적용
* 김예진 - 북마크 생성, 조회, 삭제 / 마이페이지 수정, 비밀번호 수정, 조회



### 🧪테스트 방법
**************
* RoomRoomBnB 프로젝트의 기능 테스트는 API 명세서에 정의된 엔드포인트를 활용하여 진행됩니다. 테스트 과정에서는 Postman을 사용하여 해당 엔드포인트로 요청을 보내고, 응답을 검증합니다.

| 기능 | API URL | Method | Request Header | Request | Response | Response header |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 로그인 | /login | POST |  | { ‘email’: ‘이메일’, password’: ‘비밀번호’ } | {statusCode’: ‘상태코드’, ’msg: ‘서버메세지’, } | Authorization: Bearer + 토큰값 |

![스크린샷 2024-02-15 오후 2 54 14](https://github.com/RoomRoomBnB/roombnb/assets/47919911/d34b2b6f-2be9-4b25-a350-482880bbcaf4)

### 📜API 명세서
*********************
![스크린샷 2024-02-14 오후 8 55 27](https://github.com/RoomRoomBnB/roombnb/assets/47919911/9b1284d4-9982-4eb2-af0f-a0920960d13a)
![스크린샷 2024-02-14 오후 8 55 37](https://github.com/RoomRoomBnB/roombnb/assets/47919911/d747e95d-a2a6-4a67-8a58-0cdf4f53f540)
### 📐와이어프레임
****************
![와이어프레임](https://github.com/RoomRoomBnB/roombnb/assets/47919911/245004a2-fe13-4fd9-8c8b-8f7900b16f2a)
### 🗂️ERD DIAGRAM
*****************
![erd리드미](https://github.com/RoomRoomBnB/roombnb/assets/103111681/e369e9eb-3d78-4e9c-b8ce-4e49099970e5)


