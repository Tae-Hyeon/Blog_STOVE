package kr.co.fortice.blog.global.config;

import kr.co.fortice.blog.service.CustomUserDetailsService;
import kr.co.fortice.blog.global.session.SessionAuthenticationFilter;
import kr.co.fortice.blog.global.session.SessionProvider;
import kr.co.fortice.blog.global.session.SignInSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SignInSuccessHandler signInSuccessHandler() { return new SignInSuccessHandler();}

    @Bean
    public SessionAuthenticationFilter sessionAuthenticationFilter() throws Exception{
        SessionAuthenticationFilter sessionAuthenticationFilter = new SessionAuthenticationFilter(authenticationManager());
        sessionAuthenticationFilter.setFilterProcessesUrl("/auth/signin");
        sessionAuthenticationFilter.setAuthenticationSuccessHandler(signInSuccessHandler());
        sessionAuthenticationFilter.afterPropertiesSet();
        return sessionAuthenticationFilter;
    }
    @Bean
    public SessionProvider sessionProvider() {
        return new SessionProvider(userDetailsService, passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(sessionProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Pass
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/templates/**", "/static/**", "/js/**",
                        "/image/**", "/favicon.ico", "/layout/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(sessionAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable()
                // ????????? ????????? ???????????? ant???????????? url ??????
                .authorizeRequests()
                        .antMatchers("/**").permitAll()
                        .anyRequest().authenticated()

                // ????????? ??? ??????
                .and()
                .formLogin()
                    .loginPage("/")
                    .failureUrl("/")
                    .successForwardUrl("/")
//                    .permitAll()

                // logout ??????
                .and()
                .logout()
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/")

                // Session ?????? ????????? ??? ?????? ?????? (?????? : ALWAYS)
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .invalidSessionUrl("/")
                    .maximumSessions(1)
                        .expiredUrl("/");
    }
}