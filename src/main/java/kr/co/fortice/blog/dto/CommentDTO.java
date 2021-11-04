package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Comment;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
    Integer id;
    Integer parent;
    String name;
    String contents;
    Boolean isMine;

    public static CommentDTO of(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .parent(comment.getParent())
                .name(comment.getBlogger().getName())
                .contents(comment.getContents())
                .isMine(comment.getBlogger().getId().equals(SessionUtil.getBloggerId()))
                .build();
    }
}
