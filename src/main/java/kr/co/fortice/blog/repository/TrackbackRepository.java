package kr.co.fortice.blog.repository;

import kr.co.fortice.blog.entity.Trackback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackbackRepository extends JpaRepository<Trackback, Integer> {
}
