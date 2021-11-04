package kr.co.fortice.blog.repository;

import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByBlogAndTitle(Blog blog, String title);

    @Query("SELECT c FROM Category c " +
            "WHERE c.id = :blogId " +
            "ORDER BY c.createdAt")
    List<Category> findAllByBlogId(@Param("blogId") Integer blogId);
}
