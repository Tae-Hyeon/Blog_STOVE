package kr.co.fortice.blog.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PostDeleteListRequest {
    List<Integer> ids;
}
