# Blog_STOVE

STOVE Dev Camp 서버 전형 과제

# 사용 기술 및 환경
- localhost:8080
- Spring Boot 2.5.5
- MySQL 8.0
- 

# 필요 설정
- MySQL 8.0
  - url: jdbc:mysql://localhost:3306/blog_stove?characterEncoding=UTF-8&serverTimezone=Asia/Seoul
  - 계정: blog_developer
  - 비밀번호: qmffhrmroqkfwk2@
- image path 설정
  - application.properties  
    - server.path.image=classpath:/static/images/
    - image-server.url=/images/

# 디렉토리 구조
```
src
└─── main
     ├─── kr.co.fortice.blog
     │    ├── BlogStoveApplication.java
     │    ├── controller
     │    ├── dto
     │    │      ├─── request
     │    │      └─── response
     │    ├── service
     │    ├── repository
     │    ├── entity
     │    └── global
     │             ├─── common
     │             │         └─── GlobalVO.java
     │             ├─── config
     │             │         └─── SecurityConfig.java
     │             ├─── session
     │             ├─── exception
     │             │         ├─── GlobalExceptionHandler.java
     │             │         └─── custom
     │             └─── util
     └─── resource
                 ├─── static   
                 │    ├─── images
                 │    └─── js
                 ├─── templates
                 │    └─── fragments
                 └─── application.properties
                 
```


### 기능 정리

- 블로그 기본
  - 블로그 생성(/blog POST) --> 블로그 메인 화면
  - 블로그 메인 화면 (/@{bloggerName} GET)
- 블로그 포스팅
  - 글 작성 페이지 (/write GET)
  - 글 작성 (/write POST) --> 글 조회
  - 글 수정 페이지(/write?id= GET)
  - 글 수정 (/@{bloggerName}/{postId} POST) --> 글 조회
  - 글 조회 (/@{bloggerName}/{postId} GET)
  - 글 삭제 (/{postId} DELETE)
- 이미지 업로드 (/file POST)
- 댓글 (@{bloggerName}/{postId}/comments)
  - 댓글 작성 (/ POST)
  - 댓글 수정 (/{commentId} PATCH)
  - 댓글 삭제 (/{commentId} DELETE)

### DB Schema

- 블로거 (blogger)

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |email|STRING(32)|`UNIQUE` `NOT NULL` `NOT NULL`||이메일|
  |name|STRING(20)|`NOT NULL`||사용자 명|
  |password|STRING(60)|`NOT NULL`||비밀번호|
  |authority|STRING(10)|`NOT NULL`|"ROLE_USER"|권한|
  |last_logined_at|DATETIME||now()|최근 로그인 시간|

- 블로그 (blog)     
  - blogger 1:1 ON DELETE CASCADE ON UPDATE CASCADE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blogger_id|INTEGER|`FK blogger.id` `UNIQUE` `NOT NULL`||외부 키|
  |title|STRING(40)|`NOT NULL`||블로그 이름|
  |introduce|STRING(100)||BLANK('')|블로그 소개|
  |trackback|BIT|`NOT NULL`|1|1: 동의<br>0: 거부|


- 글 목록 (category)
  - blog 1:N ON DELETE CASCADE ON UPDATE CASCADE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blog_id|INTEGER|`FK blog.id` `NOT NULL`||블로그 id|
  |title|STRING(40)|`NOT NULL`||글 목록 제목|
  |count|INTEGER||0|글 개수|
  |created_at|DATETIME||now()|생성 시간|


- 게시글 (post)
  - 게시글을 블로그가 아닌 유저에 종속시키면 여러모로 편할 것 같다.
  - blog 1:N ON DELETE CASCADE ON UPDATE CASCADE
  - category 1:N ON DELETE CASCADE ON UPDATE CASCADE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blog_id|INTEGER|`FK blog.id` `NOT NULL`||블로그 외부 키|
  |title|STRING(40)|`NOT NULL`||글 제목|
  |contents|TEXT||BLANK('')|글 내용|
  |views|INTEGER||0|조회수|
  |created_at|DATETIME||now()|생성 시간|
  |updated_at|DATETIME||now()|수정 시간|

- 댓글 (comment)
  - post 1:N ON DELETE CASCADE ON UPDATE CASCADE
  - comment parent 삭제 시 삭제할 지 말지? (에타는 삭제 안하는데 남길까)
  - blogger 1:N ON DELETE CASCADE ON UPDATE CASCADE
    - id를 외부 키로 해서 닉네임 조회 시 조인을 해야하나 따로 컬럼을 넣어야 하나?

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |post_id|INTEGER|`FK post.id` `NOT NULL`||원글 id|
  |blogger_id|INTEGER|`FK blogger.id` `NOT NULL`||댓글 단 사람 id|
  |contents|TEXT||BLANK('')|댓글 내용|
  |secret|BIT|0|비밀 댓글<br>0: 공개<br>1: 비밀|
  |parent|INTEGER|`comment.id` `NOT NULL`|0|답글 부모<br>0: 원댓글<br>x: 부모 댓글 id|
  |created_at|DATETIME||now()|생성 시간|
  |updated_at|DATETIME||now()|수정 시간|
