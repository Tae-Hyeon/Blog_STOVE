package kr.co.fortice.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("/index")
    public String getIndexPage(@AuthenticationPrincipal User user, HashMap<String, Object> model) {
        if(user == null)
            return "index";
        model.put("email", user.getUsername());
        System.out.println(user.toString());
        System.out.println(SecurityContextHolder.getContext().toString());
        return "blog_main";
    }
}
