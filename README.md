# Blog_STOVE

STOVE Dev Camp 서버 전형 과제

### 기능 정리

- 글 전체 보기 (/blog GET)
  - 블로그 생성(/ POST)
  - 블로그 메인 화면 (/{blogId} GET)
- 블로그 포스팅 (/post)
  - 글 작성 (/ POST)
  - 글 수정 (/{postId} PUT)
  - 글 삭제 (/{postId} DELETE)
  - 트랙백 (/trackback POST)
    - 특정 게시글에 나의 게시글을 엮인 글로 등록하는 것
- 블로그 글 목록
  - 목록 생성 (/blog/{blogId}/list POST)
  - 목록에 글 등록 (/blog/{blogId}/list/{listId} POST)
  - 목록 삭제 (/blog/{blogId}/list DELETE)
  - 글 목록으로 보기 (/blog/{blogId}/list/{listId} GET)
- 블로그 기타
  - 다른 블로그 팔로우(새 글 목록) (필수 X)
  - 최근 등록 댓글 (필수 X)
  - 글 태그로 보기 (필수 X)
- 댓글 (/post/{postId}/comment)
  - 댓글 작성 (/ POST)
  - 댓글 수정 (/{commentId} PATCH)
  - 댓글 삭제 (/{commentId} DELETE)
  - 답글 기능 (필수 X)
  - 비밀 기능 (필수 X)
- 관리자 도구 (/setting)
  - 블로그 설정 (/blog-infomation PUT or 각각 PATCH)
  - 트랙백 허용 설정 (/trackback PATCH)
  - 팔로우 설정 (필수 X)
  - 사용자 차단 (필수 X)
- RSS (/rss)
  - 블로그 컨텐츠를 요약해서 외부 검색 엔진에 노출시키는 방법?
  - 네이버 블로그 에서 RSS 파일을 보니 해당 블로그 정보 요약, 최근 댓글 링크 등의 내용을 갖고있다
  
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
  |subtitle|STRING(100)||BLANK('')|블로그 소개|
  |trackback|TINYINT(1)|`NOT NULL`|0|0: 동의<br>1: 거부|


- 글 목록 (list)
  - blog 1:N ON DELETE CASCADE ON UPDATE CASCADE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blog_id|INTEGER|`FK blog.id` `NOT NULL`||블로그 id|
  |title|STRING(40)|`NOT NULL`||글 목록 제목|
  |count|INTEGER||0|글 개수|
  |created_at|DATETIME||now()|생성 시간|


- 게시글 (post)
  - blog 1:N ON DELETE CASCADE ON UPDATE CASCADE
  - list 1:N ON DELETE CASCADE ON UPDATE CASCADE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blog_id|INTEGER|`FK blog.id` `NOT NULL`||블로그 외부 키|
  |list_id|INTEGER|`FK list.id` `NOT NULL`||글 목록 외부 키|
  |title|STRING(40)|`NOT NULL`||글 제목|
  |contents|TEXT||BLANK('')|글 내용|
  |trackback_link|`UNIQUE` `NOT NULL`|기본 생성해줘야 함|트랙백 링크를 위한 식별|
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
  |parent|INTEGER|`comment.id` `NOT NULL`|0|답글 부모<br>0: 원댓글<br>x: 부모 댓글 id|
  |created_at|DATETIME||now()|생성 시간|
  |updated_at|DATETIME||now()|수정 시간|

- 트랙백 (trackback) 
  - 트랙백 기능을 관계 테이블로 나눠도 되지만, 트랙백 받는 posts에서 JSON으로 관리해도 될 것 같음
  - post 1:N ON DELETE CASCADE ON UPDATE CASCADE
  - post_id_linked_id_UNIQUE UNIQUE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |post_id|INTEGER|`FK post.id` `NOT NULL`||트랙백 주체 id|
  |linked_id|INTEGER|`FK post.id` `NOT NULL`||연결된 글 id|  
  |created_at|DATETIME||now()|생성 시간|

// 제일 나중에 구현
- 팔로우 (follow)
  - blogger 1:N ON DELETE CASCADE ON UPDATE CASCADE
  - blogger_id_blocked_id_UNIQUE UNIQUE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blogger_id|INTEGER|`FK blogger.id` `NOT NULL`||팔로우 주체 id|
  |block_id|INTEGER|`FK blogger.id` `NOT NULL`||팔로우 된 id|  

// 제일 나중에 구현
- 차단 (block)
  - blogger 1:N ON DELETE CASCADE ON UPDATE CASCADE
  - blogger_id_follow_id_UNIQUE UNIQUE

  |컬럼 명|타입|제약조건|디폴트 값|설명|
  |--|--|--|--|--|
  |id|INTEGER|`PK` `AUTO_INCREMENT`||인덱스|
  |blogger_id|INTEGER|`FK blogger.id` `NOT NULL`||차단 주체 id|
  |follow_id|INTEGER|`FK blogger.id` `NOT NULL`||차단 된 id|