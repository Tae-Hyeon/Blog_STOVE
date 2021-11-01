package kr.co.fortice.blog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @ManyToOne
    @JoinColumn(name = "blogger_id")
    private Blogger blogger;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "trackback_agree")
    private Boolean trackbackAgree;

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