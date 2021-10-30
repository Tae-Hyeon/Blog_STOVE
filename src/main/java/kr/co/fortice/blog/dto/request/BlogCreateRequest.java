package kr.co.fortice.blog.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlogCreateRequest {
    @NotBlank
    String title;

    @NotBlank
    String subtitle;
}
