package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.request.BlogCreateRequest;
import kr.co.fortice.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Object getBlogMain(String name) {

        return "";
    }

    public Object createBlog(BlogCreateRequest request) {

        return "";
    }
}
