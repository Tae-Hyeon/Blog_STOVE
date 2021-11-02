package kr.co.fortice.blog.global.session;

import kr.co.fortice.blog.dto.BlogInfoDTO;
import kr.co.fortice.blog.entity.Blog;
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

    public static String getBloggerName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    public static Integer getBlogId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getBlog().getId();
    }

    public static BlogInfoDTO getBlogInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return BlogInfoDTO.of(userDetails.getBlog());
    }

    public static Blog getBlogEntity() {
        return Blog.builder()
                .id(getSessionBloggerId())
                .build();
    }
}
