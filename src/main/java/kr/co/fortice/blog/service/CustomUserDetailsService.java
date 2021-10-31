package kr.co.fortice.blog.service;

import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.repository.BloggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return bloggerRepository.findBloggerByEmail(email)
                .map(this::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User toUserDetails(Blogger blogger) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(blogger.getAuthority().toString());

        return new User(
                String.valueOf(blogger.getId()),
                blogger.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
