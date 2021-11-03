package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Post;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class PostInfoDTO {
    private Integer id;
    private String title;
    private String contents;
    private Timestamp created_at;

    public static PostInfoDTO of(Post post) {
        return PostInfoDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .created_at(post.getCreatedAt())
                .build();
    }
}
