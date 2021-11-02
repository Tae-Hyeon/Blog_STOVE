package kr.co.fortice.blog.session;

import kr.co.fortice.blog.dto.BlogInfoDTO;
import kr.co.fortice.blog.entity.Blog;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class SessionBlogVo {
    private final Integer id;
    private final String title;
    private final String introduce;

    public static SessionBlogVo of(Blog blog) {
        return SessionBlogVo.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .introduce(blog.getIntroduce())
                .build();
    }
}
