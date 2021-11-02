package kr.co.fortice.blog.repository;

import kr.co.fortice.blog.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Integer> {
    Optional<Blogger> findBloggerById(Integer id);
    Optional<Blogger> findBloggerByEmail(String email);
    Optional<Blogger> findBloggerByName(String name);
}
