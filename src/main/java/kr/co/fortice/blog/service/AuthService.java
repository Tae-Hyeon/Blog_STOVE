package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.BloggerInfoDTO;
import kr.co.fortice.blog.dto.request.SignInRequest;
import kr.co.fortice.blog.dto.request.SignUpRequest;
import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.exception.custom.AlreadySignedUpEmailException;
import kr.co.fortice.blog.repository.BloggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final BloggerRepository bloggerRepository;

    public String signup(SignUpRequest request){
        if(bloggerRepository.findBloggerByEmail(request.getEmail()).isPresent())
            throw new AlreadySignedUpEmailException("이미 등록된 사용자 입니다.");

        Blogger newBlogger = request.toBlogger(passwordEncoder);
        bloggerRepository.save(newBlogger);
        return "index";
    }

    public BloggerInfoDTO getBloggerInfo(Integer bloggerId) throws Exception{
        return BloggerInfoDTO.of(
                bloggerRepository.findBloggerById(bloggerId)
                        .orElseThrow(ChangeSetPersister.NotFoundException::new)
        );
    }
}
