package kr.co.fortice.blog.dto;

import kr.co.fortice.blog.entity.Blogger;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class BloggerInfoDTO {
    private Integer id;

    private String email;

    private String name;

    public static BloggerInfoDTO of(Blogger blogger) {
        return BloggerInfoDTO.builder()
                .id(blogger.getId())
                .email(blogger.getEmail())
                .name(blogger.getName())
                .build();
    }
}
