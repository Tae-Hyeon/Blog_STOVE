package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.global.session.SessionBlogVo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogInfoDTO {
    Integer id;
    String title;
    String introduce;

    public static BlogInfoDTO of(Blog blog) {
        return BlogInfoDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .introduce(blog.getIntroduce())
                .build();
    }

    public static BlogInfoDTO of(SessionBlogVo blogVo) {
        return BlogInfoDTO.builder()
                .id(blogVo.getId())
                .title(blogVo.getTitle())
                .introduce(blogVo.getIntroduce())
                .build();
    }
}
