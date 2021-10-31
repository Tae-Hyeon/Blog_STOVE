package kr.co.fortice.blog.config;

import kr.co.fortice.blog.session.SessionAuthenticationFilter;
import kr.co.fortice.blog.session.SessionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class SessionSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final AuthenticationManager authenticationManager;
    //private final SessionProvider sessionProvider;

    @Override
    public void configure(HttpSecurity http) {
        SessionAuthenticationFilter customSessionFilter = new SessionAuthenticationFilter(authenticationManager);
        http.addFilterBefore(customSessionFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
