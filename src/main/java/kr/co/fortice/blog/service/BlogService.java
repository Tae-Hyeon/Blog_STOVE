package kr.co.fortice.blog.service;

import kr.co.fortice.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Object getBlogMain(Integer blogId) {

        return "";
    }

    public Object createBlog() {
        return "";
    }
}
