package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping(value = "/{blogId}")
    public ResponseEntity getBlogMain( @PathVariable("blogId") Integer blogId ) {

        return ResponseEntity.ok(getBlogMain(blogId));
    }

    @PostMapping(value = "/")
    public ResponseEntity createBlog() {
        return ResponseEntity.ok(createBlog());
    }
}
