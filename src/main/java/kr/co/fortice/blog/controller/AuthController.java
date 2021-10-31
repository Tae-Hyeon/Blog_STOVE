package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.request.SignInRequest;
import kr.co.fortice.blog.dto.request.SignUpRequest;
import kr.co.fortice.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public String signin(@Valid @ModelAttribute("request") SignInRequest request, Model model, Authentication authentication) {
        //authService.signin(request);
        System.out.println(request.getEmail() + '\n');
//        System.out.println(authentication.toString());
        System.out.println(SecurityContextHolder.getContext().toString());
//        if(authentication.isAuthenticated()) {
//            model.addAttribute("name", authentication.getAuthorities());
//            model.addAttribute("detail", authentication.getDetails());
//            model.addAttribute("id", authentication.getPrincipal());
//        }
        return "blog_main";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("request") SignUpRequest request, Model model) throws Exception{
        return authService.signup(request);
    }
}
