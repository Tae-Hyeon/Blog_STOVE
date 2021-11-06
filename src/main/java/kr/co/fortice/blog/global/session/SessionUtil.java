package kr.co.fortice.blog.global.session;

import kr.co.fortice.blog.dto.BlogInfoDTO;
import kr.co.fortice.blog.entity.Blog;
import kr.co.fortice.blog.entity.Blogger;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class SessionUtil {

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static Integer getBloggerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return -1;
//            throw new UnauthenticatedException("인증이 되지 않은 블로거입니다.");
        }

        return Integer.parseInt(authentication.getName());
    }

    public static Blogger getBloggerEntity() {
        return Blogger.builder()
                .id(getBloggerId())
                .build();
    }

    public static String getBloggerName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.isAuthenticated() || authentication.getName().equals("anonymousUser"))
            return "";

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getNickname();
    }

    public static void updateBlogInfo(Blog blog) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userDetails.setBlog(SessionBlogVo.of(blog));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        System.out.println(getBlogId());
    }

    public static Integer getBlogId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if(userDetails.getBlog() == null)
            return null;
        return userDetails.getBlog().getId();
    }

    public static BlogInfoDTO getBlogInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return BlogInfoDTO.of(userDetails.getBlog());
    }

    public static Blog getBlogEntity() {
        return Blog.builder()
                .id(getBlogId())
                .build();
    }

}
