package kr.co.fortice.blog.dto.request;

import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Blogger;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class BlogCreateRequest {
    @NotBlank
    String title;

    @NotBlank
    String subtitle;

    public Blog toEntity(Blogger blogger) {
        return Blog.builder()
                .blogger(blogger)
                .title(this.title)
                .subtitle(this.subtitle)
                .trackbackAgree(true)
                .build();
    }
}
