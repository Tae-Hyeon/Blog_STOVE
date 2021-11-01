package kr.co.fortice.blog.dto.response;

import kr.co.fortice.blog.dto.BloggerInfoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogMainResponse {
    BloggerInfoDTO blogger;
    List<PostSimpleInfoDTO> posts;
}
