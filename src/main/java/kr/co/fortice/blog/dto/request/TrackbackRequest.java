package kr.co.fortice.blog.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TrackbackRequest {
    @NotNull
    Integer linkedPostId;
}
