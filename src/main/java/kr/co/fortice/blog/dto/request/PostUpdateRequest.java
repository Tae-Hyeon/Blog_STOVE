package kr.co.fortice.blog.dto.request;

import kr.co.fortice.blog.entity.Category;
import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostUpdateRequest {
    @NotNull
    private Integer id;

    private int category;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    public Post toEntity() {
        return Post.builder()
                .blog(SessionUtil.getBlogEntity())
                //.category((this.category == 0) ? null : Category.builder().id(category).build())
                .title(this.title)
                .contents(this.contents)
                .build();
    }
}
