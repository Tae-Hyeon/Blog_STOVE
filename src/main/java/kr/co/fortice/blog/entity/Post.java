package kr.co.fortice.blog.entity;

import kr.co.fortice.blog.dto.request.PostUpdateRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //TODO: Blog에 종속시키기보다 Blogger에 종속시키는게 좋아보인다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category")
//    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "views")
    private Integer views;
    //TODO: 비밀글 관련 설정 추가

    @Column(name = "created_at")
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    List<Comment> comments = new ArrayList<>();

    public void plusViews() {
        this.views += 1;
    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();
    }
}
