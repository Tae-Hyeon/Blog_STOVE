package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class PostInfoDTO {
    private Integer id;
    private String title;
    private String bloggerName;
    private String contents;
    private Boolean isMine;
    private Timestamp created_at;

    public static PostInfoDTO of(Post post) {
        return PostInfoDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .bloggerName(post.getBlog().getBlogger().getName())
                .contents(post.getContents())
                .isMine(post.getBlog().getId().equals(SessionUtil.getBlogId()))
                .created_at(post.getCreatedAt())
                .build();
    }
}
