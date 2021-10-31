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
@Table(name = "blogger")
public class Blogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.REMOVE)
    List<Blog> blogs = new ArrayList<>();

    public enum Authority {
        ROLE_BLOGGER, ROLE_ADMIN
    }
}
