package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.PostInfoDTO;
import kr.co.fortice.blog.dto.request.PostCreateRequest;
import kr.co.fortice.blog.global.common.GlobalVO;
import kr.co.fortice.blog.global.session.SessionUtil;
import kr.co.fortice.blog.service.CategoryService;
import kr.co.fortice.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping(value = "/write")
    public String getWritePostPage(Model model) {
        model.addAttribute("postForm", new PostCreateRequest());
        model.addAttribute("categories", categoryService.getCategories());
        return "post_write"; //PageList.POST_CREATE_PAGE.resource();
    }

    @PostMapping("/write")
    public String createPost(@ModelAttribute("request") PostCreateRequest request, Model model) {
        //model.addAttribute("post", postService.createPost(request));
        PostInfoDTO postInfoDTO = postService.createPost(request);
        return "redirect:/@" + SessionUtil.getBloggerName() + '/' + postInfoDTO.getId(); //PageList.POST_READ_PAGE.resource();
    }

    @GetMapping("/@{bloggerName}/{postId}")
    public String getPost(@PathVariable("postId") Integer postId, Model model) {
        model.addAttribute("post", postService.getPost(postId));
        return "post"; //PageList.POST_READ_PAGE.resource();
    }

    @PostMapping(value = "/file", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ResponseEntity<String> uploadImage(@RequestPart MultipartFile image, Model model) throws IOException {
        String imageURL = postService.uploadImage(image);
        if(!imageURL.isBlank())
            return ResponseEntity.ok(GlobalVO.getImageServerURL() + imageURL);
        return ResponseEntity.ok(imageURL);
    }
}
