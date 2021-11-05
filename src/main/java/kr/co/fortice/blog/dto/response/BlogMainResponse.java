package kr.co.fortice.blog.dto.response;

import kr.co.fortice.blog.dto.BlogInfoDTO;
import kr.co.fortice.blog.dto.BloggerInfoDTO;
import kr.co.fortice.blog.dto.PostSimpleInfoDTO;
import kr.co.fortice.blog.entity.Blogger;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
public class BlogMainResponse {
    private BloggerInfoDTO blogger;
    private BlogInfoDTO blog;
    private List<PostSimpleInfoDTO> posts;

    public static BlogMainResponse of(Blogger blogger) {
        if(blogger.getBlog() == null)
            return BlogMainResponse.builder()
                    .blogger(BloggerInfoDTO.of(blogger))
                    .build();
        
        return BlogMainResponse.builder()
                .blogger(BloggerInfoDTO.of(blogger))
                .blog(BlogInfoDTO.of(blogger.getBlog()))
                .posts(blogger.getBlog().getPosts().stream()
                        .map(PostSimpleInfoDTO::of)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
