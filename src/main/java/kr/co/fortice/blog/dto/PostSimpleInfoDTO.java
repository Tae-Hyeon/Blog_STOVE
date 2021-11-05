package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.common.GlobalVO;
import kr.co.fortice.blog.global.util.StringUtil;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostSimpleInfoDTO {
    private Integer id;
    private String title;
    private String bloggerName;
    private String summary;
    private String image;

    public static PostSimpleInfoDTO of(Post post) {
        return PostSimpleInfoDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .bloggerName(post.getBlog().getBlogger().getName())
                .summary(StringUtil.parseSummary(post.getContents()))
                .image(StringUtil.getFirstImage(post.getContents()))
                .build();
    }
}
