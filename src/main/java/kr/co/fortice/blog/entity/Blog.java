package kr.co.fortice.blog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "blogger_id", unique = true)
    private Blogger blogger;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "trackback_agree")
    private Boolean trackbackAgree;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    List<Post> posts = new ArrayList<Post>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void agreeTrackback() {
        this.trackbackAgree = true;
    }

    public void disagreeTrackback() {
        this.trackbackAgree = false;
    }
}