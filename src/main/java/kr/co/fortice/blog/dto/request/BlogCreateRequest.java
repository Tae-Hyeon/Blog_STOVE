package kr.co.fortice.blog.dto.request;

import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Blogger;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
public class BlogCreateRequest {
    @NotBlank
    String title;

    @NotBlank
    String introduce;

    public Blog toEntity(Blogger blogger) {
        return Blog.builder()
                .blogger(blogger)
                .title(this.title)
                .introduce(this.introduce)
                .trackbackAgree(true)
                .build();
    }
}
