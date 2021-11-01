package kr.co.fortice.blog.session;

import kr.co.fortice.blog.exception.custom.UnauthenticatedException;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class SessionUtil {

    public static Integer getSessionBloggerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return -1;
//            throw new UnauthenticatedException("인증이 되지 않은 블로거입니다.");
        }

        return Integer.parseInt(authentication.getName());
    }

    private Boolean isAuthenticationNull(Authentication authentication) {
        return (authentication == null || authentication.getName() == null);
    }
}
