package kr.co.fortice.blog.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blogId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "count")
    private Integer count;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Post> posts = new ArrayList<Post>();

    @Column(name = "created_at")
    @CreationTimestamp
    private java.sql.Timestamp createdAt;
}
