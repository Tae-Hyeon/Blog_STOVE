package kr.co.fortice.blog.dto.request;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
public class SignInRequest {
    @NotBlank
    String email;

    @NotBlank
    String password;

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
