package kr.co.fortice.blog.dto.request;

import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Category;
import kr.co.fortice.blog.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
public class PostCreateRequest {
    private int category;
    private String title;
    private String contents;

    public Post toEntity(Blog blog, Category category) {
        return Post.builder()
                .blog(blog)
                .category(category)
                .title(this.title)
                .contents(this.contents)
                .build();
    }
}
