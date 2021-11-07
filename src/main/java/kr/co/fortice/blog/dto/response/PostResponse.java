package kr.co.fortice.blog.dto.response;

import kr.co.fortice.blog.dto.BloggerInfoDTO;
import kr.co.fortice.blog.dto.CommentDTO;
import kr.co.fortice.blog.dto.PostInfoDTO;
import kr.co.fortice.blog.dto.PostSimpleInfoDTO;
import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.entity.Trackback;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostResponse {
    PostInfoDTO post;
    List<CommentDTO> comments;
    List<PostSimpleInfoDTO> linkedPosts;

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .post(PostInfoDTO.of(post))
                .comments(post.getComments().stream()
                        .map(CommentDTO::of)
                        .collect(Collectors.toList())
                )
                .linkedPosts(post.getTrackbacks().stream()
                        .map(Trackback::getLinkedPost)
                        .map(PostSimpleInfoDTO::of)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
