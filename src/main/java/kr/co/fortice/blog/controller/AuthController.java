package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.request.SignUpRequest;
import kr.co.fortice.blog.service.AuthService;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("request") SignUpRequest request, Model model) throws Exception{
        authService.signup(request);
        return PageList.INDEX_PAGE.resource();
    }

    @PostMapping("/signin")
    public String signin(HashMap<String, Object> model) throws Exception{
        Integer bloggerId = SessionUtil.getSessionBloggerId();

        model.put("isAuthenticated", true);
        model.put("blogger", authService.getBloggerInfo(bloggerId));

        System.out.println(model.toString());

        return PageList.INDEX_PAGE.resource();
    }
}
