package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.request.BlogCreateRequest;
import kr.co.fortice.blog.dto.response.BlogMainResponse;
import kr.co.fortice.blog.service.BlogService;
import kr.co.fortice.blog.global.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Controller
public class BlogController {
    private final BlogService blogService;

    @GetMapping(value = "/blog")
    public String getBlogCreatePage() {
        return PageList.BLOG_CREATE_PAGE.resource();
    }

    @PostMapping(value = "/blog")
    public String createBlog(@ModelAttribute("request") BlogCreateRequest request) throws UnsupportedEncodingException {
        return "redirect:/" + URLEncoder.encode(blogService.createBlog(request), "UTF-8");
    }

    @GetMapping(value = "/@{bloggerName}")
    public String getBlogMain(@PathVariable("bloggerName") String bloggerName, Model model) throws Exception {
        //TODO: 본인 소유의 블로그인지 확인해야함
        BlogMainResponse mainResponse = blogService.getBlogMain(bloggerName);
        System.out.println(bloggerName.equals(SessionUtil.getBloggerName()));
        if (SessionUtil.isAuthenticated() && bloggerName.equals(SessionUtil.getBloggerName())) {
            if (mainResponse.getBlog() == null)
                return "redirect:/blog";
            model.addAttribute("isMine", true);
        } else
            model.addAttribute("isMine", false);
        model.addAttribute("blogger", mainResponse.getBlogger());
        model.addAttribute("blog", mainResponse.getBlog());
        model.addAttribute("posts", mainResponse.getPosts());
        return "blog_main"; //PageList.BLOG_MAIN_PAGE.resource();
    }
}
