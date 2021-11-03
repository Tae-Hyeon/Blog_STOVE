package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.PostInfoDTO;
import kr.co.fortice.blog.dto.request.PostCreateRequest;
import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Category;
import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.exception.custom.DataNotFoundException;
import kr.co.fortice.blog.global.util.FileUtil;
import kr.co.fortice.blog.repository.CategoryRepository;
import kr.co.fortice.blog.repository.PostRepository;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PostService {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostInfoDTO createPost(PostCreateRequest request) {
        Category category = categoryRepository.findCategoryByBlogAndTitle(Blog.builder().id(1).build(), request.getTitle())
                .orElse(Category.builder().id(-1).build());
                //.orElseThrow(DataNotFoundException::new);
        Post post;
        if(category.getId() == -1)
            post = request.toEntity(SessionUtil.getBlogEntity(), null);
        else
            post = request.toEntity(category.getBlog(), category);
        postRepository.save(post);
        return PostInfoDTO.of(post);
    }
    
    public PostInfoDTO getPost(Integer postId) {
        return PostInfoDTO.of(
                postRepository.findPostById(postId)
                        .orElseThrow(DataNotFoundException::new)
        );
    }
    public String uploadImage(MultipartFile image) throws IOException {
        return FileUtil.uploadFile(image);
    }

}
