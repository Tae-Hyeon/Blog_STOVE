package kr.co.fortice.blog.dto.response;

import kr.co.fortice.blog.dto.BloggerInfoDTO;
import kr.co.fortice.blog.dto.CommentDTO;
import kr.co.fortice.blog.dto.PostInfoDTO;
import kr.co.fortice.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostResponse {
    PostInfoDTO post;
    List<CommentDTO> comments;

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .post(PostInfoDTO.of(post))
                .comments(post.getComments().stream()
                        .map(CommentDTO::of)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
