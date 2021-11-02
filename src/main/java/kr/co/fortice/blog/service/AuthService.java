package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.BloggerInfoDTO;
import kr.co.fortice.blog.dto.request.SignUpRequest;
import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.global.exception.custom.AlreadySignedUpEmailException;
import kr.co.fortice.blog.global.exception.custom.DataNotFoundException;
import kr.co.fortice.blog.repository.BloggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final BloggerRepository bloggerRepository;

    @Transactional
    public void signup(SignUpRequest request){
        if(bloggerRepository.findBloggerByEmail(request.getEmail()).isPresent())
            throw new AlreadySignedUpEmailException("이미 등록된 사용자 입니다.");

        Blogger newBlogger = request.toBlogger(passwordEncoder);
        bloggerRepository.save(newBlogger);
    }

    public BloggerInfoDTO getBloggerInfo(Integer bloggerId) throws Exception{
        return BloggerInfoDTO.of(
                bloggerRepository.findBloggerById(bloggerId)
                        .orElseThrow(DataNotFoundException::new)
        );
    }
}
