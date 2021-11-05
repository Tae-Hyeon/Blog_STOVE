package kr.co.fortice.blog.dto.request;

import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.entity.Comment;
import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentCreateRequest {
    private Integer parent;

    @NotBlank
    private String contents;

    public Comment toEntity(Integer postId) {
        return Comment.builder()
                .blogger(Blogger.builder().id(SessionUtil.getBloggerId()).build())
                .post(Post.builder().id(postId).build())
                .parent(this.parent)
                .contents(this.contents)
                .build();
    }
}
