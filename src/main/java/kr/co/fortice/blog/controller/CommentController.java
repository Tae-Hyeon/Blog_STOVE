package kr.co.fortice.blog.controller;

import kr.co.fortice.blog.dto.CommentDTO;
import kr.co.fortice.blog.dto.request.CommentCreateRequest;
import kr.co.fortice.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/@{bloggerName}/{postId}/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<CommentDTO>> getComments(
            @PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @PostMapping
    public @ResponseBody ResponseEntity<CommentDTO> createComment(
            @PathVariable("postId") Integer postId,
            @ModelAttribute("request") CommentCreateRequest request) {
        return ResponseEntity.ok(commentService.createComment(postId, request));
    }

    @PatchMapping("/{commentId}")
    public @ResponseBody ResponseEntity<Object> patchComment(
            @PathVariable("commentId") Integer commentId,
            @RequestParam String contents) {
        commentService.patchContents(commentId, contents);
        return ResponseEntity.ok("댓글 수정 성공");
    }

    @DeleteMapping("/{commentId}")
    public @ResponseBody ResponseEntity<Object> deleteComment(
            @PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제 성공");
    }
}
