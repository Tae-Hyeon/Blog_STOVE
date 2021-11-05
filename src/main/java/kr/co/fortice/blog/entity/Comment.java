package kr.co.fortice.blog.entity;

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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "blogger_id", nullable = false)
    private Blogger blogger;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "created_at")
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

    public void patchContents(String contents) {
        this.contents = contents;
    }
}
