package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.service.AuthService;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {
    private final AuthService authService;

    @GetMapping
    public String getIndexPage(HashMap<String, Object> model) throws Exception{
        Integer bloggerId = SessionUtil.getSessionBloggerId();
        System.out.println(bloggerId);
        if(bloggerId == -1) {
            model.put("isAuthenticated", false);
            return PageList.INDEX_PAGE.resource();
        }

        return getSignedPage(model, bloggerId);
    }

    private String getSignedPage(HashMap<String, Object> model, Integer bloggerId) throws Exception {
        model.put("isAuthenticated", true);
        model.put("blogger", authService.getBloggerInfo(bloggerId));
        System.out.println(bloggerId);
        return PageList.INDEX_PAGE.resource();
    }
}
