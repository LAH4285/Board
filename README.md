게시판 구현
===
### 개요
- SpringFramework를 이용한 게시판 구현 프로젝트  
### 일정  
- 23.11.28 ~ 23.12.03
+ 개인 프로젝트  
- - - - - - - - -
### 사용 기술 및 개발 환경
- O/S : window11 Home 22H2
- DB : MySQL WORKBENCH 8.0.35 CE  
- Framework/Flatform : Spring Data JPA, Thymeleaf
- Spring Boot 2.6.7
- JDK 11
- Language : JAVA, HTML5  
- IDE: IntelliJ IDEA Community
- - - - - - - - - - - - - -
### 구현 기능

- 회원가입 및 로그인 구현
- 게시글 작성 및 수정
- 댓글 기능 구현
- 파일 업로드 및 다운로드 기능 구현

1. 글쓰기(/board/save)
2. 글목록(/board/)
3. 글조회(/board/{id})
> ※ 댓글
> 1. 댓글작성
> > - 글 조회 기능에 댓글 작성 구현
> 2. 댓글 수정 및 삭제
> > - 작성된 댓글 수정 및 삭제 기능 구현(hidden)


4. 글수정(/board/update/{id})
 - 상세화면에서 수정 버튼 클릭
 - 서버에서 해당 게시글의 정보를 가지고 수정 화면 출력
 - 제목, 내용 수정 입력 받아서 서버로 요청
>
> ※ 수정 처리
> 1. 글삭제(/board/delete/{id})
> 2. 페이징처리(/board/paging)
>  > - /board/paging?page=1
>  > - /board/paging/1
>
> 3. 게시글
> > - 한페이지에 5개씩 => 3개
> > - 한페이지에 3개씩 => 5개
> 4. 파일(이미지)첨부하기
> 5. 단일 파일 첨부
> 6. 다중 파일 첨부

5. 글삭제(/board/delete/{id})

### ※ 향후 업데이트 예정 기능

1. 회원가입 및 로그인 구현
2. Spring Security
 - JWT토큰사용
 - PasswordEncoder기능 추가
