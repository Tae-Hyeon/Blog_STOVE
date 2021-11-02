package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Blog;
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
}
