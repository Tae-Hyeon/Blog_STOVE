package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.PostInfoDTO;
import kr.co.fortice.blog.dto.PostSimpleInfoDTO;
import kr.co.fortice.blog.dto.request.PostCreateRequest;
import kr.co.fortice.blog.dto.request.PostUpdateRequest;
import kr.co.fortice.blog.dto.response.PostResponse;
import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Category;
import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.exception.custom.DataNotFoundException;
import kr.co.fortice.blog.global.util.FileUtil;
import kr.co.fortice.blog.repository.CategoryRepository;
import kr.co.fortice.blog.repository.PostRepository;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @Transactional
    public Integer createPost(PostUpdateRequest request) {
        Post post = request.toEntity();
        postRepository.save(post);

        return post.getId();
    }

    public Integer updatePost(PostUpdateRequest request) {

        Post post = postRepository.findPostById(request.getId())
                        .orElseThrow(DataNotFoundException::new);

        if(post.getBlog().getId().equals(SessionUtil.getBlogId())) {
            post.update(request);
            post = postRepository.save(post);
        }
        else
            post = postRepository.save(request.toEntity());

        return post.getId();
    }

    public PostResponse getPost(Integer postId) {
        return PostResponse.of(
                postRepository.findPostById(postId)
                        .orElseThrow(DataNotFoundException::new)
        );
    }

    public PostInfoDTO getPostWithOutComments(Integer postId) {
        return PostInfoDTO.of(
                postRepository.findPostById(postId)
                        .orElseThrow(DataNotFoundException::new)
        );
    }

    public List<PostSimpleInfoDTO> getLatestTenPosts() {
        //TODO: 가능하면 최신 말고 추천, 캐시
        return postRepository.findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(PostSimpleInfoDTO::of)
                .collect(Collectors.toList());
    }

    public void deletePost(Integer postId) {
        Post post = postRepository.findPostById(postId)
                .orElseThrow(DataNotFoundException::new);
        if(post.getBlog().getId().equals(SessionUtil.getBlogId()))
            postRepository.deleteById(postId);
    }

    public String uploadImage(MultipartFile image) throws IOException {
        return FileUtil.uploadFile(image);
    }

}
