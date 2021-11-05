package kr.co.fortice.blog.service;

import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.repository.BloggerRepository;
import kr.co.fortice.blog.global.session.CustomUserDetails;
import kr.co.fortice.blog.global.session.SessionBlogVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final BloggerRepository bloggerRepository;

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Blogger> blogger = bloggerRepository.findBloggerByEmail(email);

        if (blogger.isEmpty()) {
            Blogger newBlogger = bloggerRepository.save(Blogger.builder()
                    .id(10)
                    .email("email@folog.co.kr")
                    .password("$2a$10$L7Xx9jFDQhn8BDBPL6RFau08zSg/Bmuq3B4I9.4GrxsQObV1ZyuOy")
                    .name("한태현test")
                    .authority(Blogger.Authority.ROLE_BLOGGER)
                    .build());
            return toUserDetails(newBlogger);
        }

        return bloggerRepository.findBloggerByEmail(email)
                .map(this::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private CustomUserDetails toUserDetails(Blogger blogger) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(blogger.getAuthority().toString());

        return new CustomUserDetails(
                SessionBlogVo.of(blogger.getBlog()),
                String.valueOf(blogger.getId()),
                blogger.getEmail(),
                blogger.getName(),
                blogger.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
