Springboot Framework를 활용한 게시판 구현V1.4.2
===
### 개요
-SpringFramework를 이용한 게시판 구현 프로젝트  
### 일정  
 -23.11.21 ~ 23.12.03

 -개인 프로젝트  
- - - - - - - - -
### 사용 기술 및 개발 환경
-O/S : window11 Home 22H2
-DB : MySQL WORKBENCH 8.0.35 CE   
-Framework/Flatform : Spring Data JPA, Thymeleaf  
-Spring Boot 2.6.7  
-JDK 11, Gradle   
-Language : JAVA, HTML5   
-IDE: IntelliJ IDEA Community
- - - - - - - - - - - - - -
### 구현 기능
1. 글쓰기(/board/save)  
<div class="portfolio1.png">
  <img src="/image/portfolio1.png".>
</div>
2. 글목록(/board/)  
<div class="portfolio2.png">
  <img src="/image/portfolio2.png".>
</div>
3. 글조회(/board/{id})  
<div class="portfolio3.png">
  <img src="/image/portfolio3.png".>
</div>
4. 글수정(/board/update/{id})  

-상세화면에서 수정 버튼 클릭    
      
 -서버에서 해당 게시글의 정보를 가지고 수정 화면 출력    
      
-제목, 내용 수정 입력 받아서 서버로 요청  
<div class="portfolio4.png">
  <img src="/image/portfolio4.png".>
</div>
<div class="portfolio5.png">
  <img src="/image/portfolio5.png".>
</div>

___※ 수정 처리___   

1. 글삭제(/board/delete/{id})       
</div>
<div class="portfolio6.png">
  <img src="/image/portfolio6.png".>
</div>
</div>
<div class="portfolio7.png">
  <img src="/image/portfolio7.png".>
</div>
2. 페이징처리(/board/paging) 

  -/board/paging?page=1  
  
  -/board/paging/1    
3. 게시글       

 -한페이지에 5개씩 => 2개    
 <div class="portfolio8.png">
   <img src="/image/portfolio8.png".>
 </div>
 <div class="portfolio9.png">
   <img src="/image/portfolio9.png".>
</div>   
4. 파일(이미지)첨부하기    
<div class="portfolio1.png">
  <img src="/image/portfolio1.png".>
</div>
  <div class="portfolio3.png">
    <img src="/image/portfolio3.png".>
</div>
5. 단일 파일 첨부    
<div class="portfolio1.png">
  <img src="/image/portfolio1.png".>
</div>
6. 다중 파일 첨부    
<div class="portfolio7.png">
  <img src="/image/portfolio7.png".>
</div>

___※ 댓글___    
 1. 댓글작성      
  
  -글 조회 기능에 댓글 작성 구현    
  <div class="portfolio10.png">
    <img src="/image/portfolio10.png".>
  </div>
  <div class="portfolio11.png">
    <img src="/image/portfolio11.png".>
  </div>
 2. 댓글 수정 및 삭제
 
-작성된 댓글 수정 및 삭제 기능 구현(hidden)

-수정    
<div class="portfolio12.png">
  <img src="/image/portfolio12.png".>
</div>
<div class="portfolio13.png">
  <img src="/image/portfolio13.png".>
</div>
-삭제    
<div class="portfolio14.png">
  <img src="/image/portfolio14.png".>
</div>
<div class="portfolio15.png">
  <img src="/image/portfolio15.png".>
</div>


###  향후 업데이트 예정 기능

1. 회원가입 및 로그인 구현
2. Spring Security    
 -JWT토큰사용   
 -PasswordEncoder기능 추가

 - - - - - - - - - - - - - -


#### v1.0.0 (2023.11.21)
1. 게시글 작성(/board/save)  
  -작성자, 제목, 내용
2. 메인 페이지 이동(/)


#### v1.1.0 (2023.11.22)
 1. 게시판 페이징 기능 구현(/board/paging)  
   -한 페이지 5개씩     
   -페이지 최대 3개씩

 2. 게시글 조회 기능 구현(/board/{id})   
-글번호, 제목, 작성일, 내용   
-목록, 수정, 삭제 버튼

#### v1.2.0 (2023.11.23)

1. 게시글 수정 기능 구현(/board/update/{id})

2. 게시글 수정 적용 기능 구현(/board/update)

3. 게시글 삭제 기능 구현(/board/{id})

#### v1.3.0 (2023.11.24)
1. 댓글 작성(/comment/save)

2. 게시글에 달린 댓글들 보이기(/comment/comments)

#### v1.3.1 (2023.11.27)
1. [수정] 게시글 작성(/board/save)   
  -파일 첨부 (단일)
2. [추가] 게시글 수정 적용(/board/update)     
  -첨부한 파일 수정 가능

#### v1.3.2 (2023.11.28)
1. [추가] 게시글에 첨부한 파일(이미지) 다운(/download/{uuid}/{filename})

2. [수정] 게시글에 들어갔을 떄 항상 댓글이 보이도록 수정

#### v1.4.0 (2023.11.29)
1. 댓글 수정 기능 구현(/comment/update/{id})

2. 댓글 삭제 기능 구현(/board/delete/{id})

#### v1.4.1 (2023.11.30)
 1. [추가] 게시글 수정 적용(/board/update)   
-첨부한 파일 수정 가능(다중)

2. 화면디자인 수정

#### v1.4.2 (2023.12.01)    
1. HTML5 알람 추가
