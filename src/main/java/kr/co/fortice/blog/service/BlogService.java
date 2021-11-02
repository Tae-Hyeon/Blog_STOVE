package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.BlogInfoDTO;
import kr.co.fortice.blog.dto.BloggerInfoDTO;
import kr.co.fortice.blog.dto.request.BlogCreateRequest;
import kr.co.fortice.blog.dto.response.BlogMainResponse;
import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.exception.custom.DataNotFoundException;
import kr.co.fortice.blog.repository.BlogRepository;
import kr.co.fortice.blog.repository.BloggerRepository;
import kr.co.fortice.blog.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public Blogger hasBlog(String bloggerName) {
        return bloggerRepository.findBloggerByName(bloggerName)
                .orElseThrow(DataNotFoundException::new);
    }

    public BlogMainResponse getBlogMain(String bloggerName) {
        Blogger blogger = bloggerRepository.findBloggerByName(bloggerName)
                .orElseThrow(DataNotFoundException::new);

        return BlogMainResponse.of(blogger);
    }

    public Object createBlog(BlogCreateRequest request) {
        Blogger blogger = bloggerRepository.findBloggerById(SessionUtil.getSessionBloggerId())
                .orElseThrow(DataNotFoundException::new);
        System.out.println(request.getTitle() + request.getIntroduce());
        Blog blog = request.toEntity(blogger);
        blogRepository.save(blog);

        return blogger.getName();
    }
}
