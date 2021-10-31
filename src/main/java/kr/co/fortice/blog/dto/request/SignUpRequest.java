package kr.co.fortice.blog.dto.request;

import kr.co.fortice.blog.entity.Blogger;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    String email;

    @NotBlank
    String name;

    @NotBlank
    String password;

    public Blogger toBlogger(PasswordEncoder passwordEncoder){
        return Blogger.builder()
                .email(this.email)
                .name(this.name)
                .password(passwordEncoder.encode(this.password))
                .authority(Blogger.Authority.ROLE_BLOGGER)
                .build();
    }
}
