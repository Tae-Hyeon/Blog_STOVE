package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.request.BlogCreateRequest;
import kr.co.fortice.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class BlogController {
    private final BlogService blogService;

    @GetMapping(value = "/{name}")
    public ResponseEntity getBlogMain(@PathVariable("name") String name) {
        blogService.getBlogMain(name);
        return ResponseEntity.ok();
    }

    @PostMapping(value = "/blog")
    public ResponseEntity createBlog(@ModelAttribute("request")BlogCreateRequest request) {

        return ResponseEntity.ok(createBlog(request));
    }
}
