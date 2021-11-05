package kr.co.fortice.blog.global.session;

import kr.co.fortice.blog.entity.Blog;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SessionBlogVo {
    private final Integer id;
    private final String title;
    private final String introduce;

    public static SessionBlogVo of(Blog blog) {
        if(blog == null)
            return null;
        return SessionBlogVo.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .introduce(blog.getIntroduce())
                .build();
    }
}
