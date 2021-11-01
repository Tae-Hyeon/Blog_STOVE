package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.request.BlogCreateRequest;
import kr.co.fortice.blog.dto.response.BlogMainResponse;
import kr.co.fortice.blog.entity.Blogger;
import kr.co.fortice.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class BlogController {
    private final BlogService blogService;


    @GetMapping(value = "/blog")
    public String getBlogCreatePage() {
        return "blog_create";
    }

    @PostMapping(value = "/blog")
    public String createBlog(@ModelAttribute("request") BlogCreateRequest request) {
        return "redirect:/" + blogService.createBlog(request);
    }

    @GetMapping(value = "/{bloggerName}")
    public String getBlogMain(@PathVariable("bloggerName") String bloggerName, Model model) throws Exception{
        Blogger blogger = blogService.hasBlog(bloggerName);
        System.out.println(blogger.getBlog() == null);
        if(blogger.getBlog() == null)
            return "blog_create";

        BlogMainResponse mainResponse = blogService.getBlogMain(bloggerName);
        model.addAttribute("blogInfo", mainResponse);
        return "blog_main";
    }
}
