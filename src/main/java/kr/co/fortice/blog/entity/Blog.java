package kr.co.fortice.blog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "blogger_id")
    private Blogger blogger;

    @NotBlank
    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "trackback_agree")
    private Byte trackbackAgree;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void agreeTrackback() {
        this.trackbackAgree = 0;
    }

    public void disagreeTrackback() {
        this.trackbackAgree = 1;
    }
}