package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.util.StringUtil;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostSimpleInfoDTO {
    String title;
    String summary;
    String image;

    public static PostSimpleInfoDTO of(Post post) {
        return PostSimpleInfoDTO.builder()
                .title(post.getTitle())
                .summary(StringUtil.parseSummary(post.getContents()))
                .image(StringUtil.getFirstImage(post.getContents()))
                .build();
    }
}
