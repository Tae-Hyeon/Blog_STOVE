package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.request.BlogCreateRequest;
import kr.co.fortice.blog.dto.response.BlogMainResponse;
import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.global.exception.custom.DataNotFoundException;
import kr.co.fortice.blog.repository.BlogRepository;
import kr.co.fortice.blog.repository.BloggerRepository;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public Optional<Blogger> hasBlog(String bloggerName) {
        return bloggerRepository.findBloggerByName(bloggerName);
                //.orElseThrow(DataNotFoundException::new);
    }

    public BlogMainResponse getBlogMain(String bloggerName) {
        Blogger blogger = bloggerRepository.findBloggerByName(bloggerName)
                .orElseThrow(DataNotFoundException::new);

        return BlogMainResponse.of(blogger);
    }

    @Transactional
    public Object createBlog(BlogCreateRequest request) {
        Blogger blogger = bloggerRepository.findBloggerById(SessionUtil.getBloggerId())
                .orElseThrow(DataNotFoundException::new);
        System.out.println(request.getTitle() + request.getIntroduce());
        Blog blog = request.toEntity(blogger);
        blogRepository.save(blog);

        return blogger.getName();
    }
}
