package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.PostInfoDTO;
import kr.co.fortice.blog.dto.request.PostCreateRequest;
import kr.co.fortice.blog.dto.request.PostUpdateRequest;
import kr.co.fortice.blog.dto.response.PostResponse;
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
    public String getWritePostPage(@RequestParam(required = false) Integer id, Model model) {
        model.addAttribute("postForm", new PostUpdateRequest());
        model.addAttribute("categories", categoryService.getCategories());
        if(id == null) {
            return "post_write"; //PageList.POST_CREATE_PAGE.resource();
        }
        model.addAttribute("post", postService.getPostWithOutComments(id));
        return "post_update";
    }

    @PostMapping("/write")
    public String createPost(@ModelAttribute("request") PostUpdateRequest request) {
        //model.addAttribute("post", postService.createPost(request));
        System.out.println(request.getId());
        System.out.println(request.getContents());
        if(request.getId() == 0)
            return "redirect:/@" + SessionUtil.getBloggerName() + '/' + postService.createPost(request); //PageList.POST_READ_PAGE.resource();
        return "redirect:/@" + SessionUtil.getBloggerName() + '/' + postService.updatePost(request); //PageList.POST_READ_PAGE.resourc
    }

    @GetMapping("/@{bloggerName}/{postId}")
    public String getPost(@PathVariable("postId") Integer postId, Model model) {
        PostResponse response = postService.getPost(postId);
        model.addAttribute("post", response.getPost());
        model.addAttribute("comments", response.getComments());
        return "post"; //PageList.POST_READ_PAGE.resource();
    }

    @DeleteMapping("/@{bloggerName}/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Integer postId, Model model) {
        postService.deletePost(postId);
        return ResponseEntity.ok("삭제 성공"); //PageList.POST_READ_PAGE.resource();
    }

    @PostMapping(value = "/file", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ResponseEntity<String> uploadImage(@RequestPart MultipartFile image, Model model) throws IOException {
        String imageURL = postService.uploadImage(image);
        if(!imageURL.isBlank())
            return ResponseEntity.ok(GlobalVO.getImageServerURL() + imageURL);
        return ResponseEntity.ok(imageURL);
    }
}
