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

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final BloggerRepository bloggerRepository;

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
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
                blogger.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
