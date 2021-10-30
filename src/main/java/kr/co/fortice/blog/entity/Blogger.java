package kr.co.fortice.blog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blogger")
public class Blogger {
    @Id
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    private String authority;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    List<Blog> blogs = new ArrayList<>();
}
