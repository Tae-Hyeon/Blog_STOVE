package kr.co.fortice.blog.repository;

import kr.co.fortice.blog.entity.Comment;
import kr.co.fortice.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentByPostOrderByCreatedAt(Post post);
}
