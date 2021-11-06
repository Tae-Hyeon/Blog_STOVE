package kr.co.fortice.blog.repository;

import kr.co.fortice.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findPostById(Integer postId);

    List<Post> findTop10ByOrderByCreatedAtDesc();

    List<Post> findAllByIdIn(List<Integer> ids);
}
