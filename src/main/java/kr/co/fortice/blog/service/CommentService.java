package kr.co.fortice.blog.service;

import kr.co.fortice.blog.dto.CommentDTO;
import kr.co.fortice.blog.dto.request.CommentCreateRequest;
import kr.co.fortice.blog.entity.Comment;
import kr.co.fortice.blog.entity.Post;
import kr.co.fortice.blog.global.exception.custom.DataNotFoundException;
import kr.co.fortice.blog.global.session.SessionUtil;
import kr.co.fortice.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDTO> getComments(Integer postId) {
        return commentRepository
                .findCommentByPostOrderByCreatedAt(Post.builder().id(postId).build())
                .stream()
                .map(CommentDTO::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDTO createComment(Integer postId, CommentCreateRequest request) {
        //TODO: if 차단 or 댓글 금지 create 금지
        return CommentDTO.of(commentRepository.save(request.toEntity(postId)));
    }

    @Transactional
    public void patchContents(Integer commentId, String contents) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(DataNotFoundException::new);
        if(comment.getBlogger().getId().equals(SessionUtil.getBloggerId())) {
            comment.patchContents(contents);
            commentRepository.save(comment);
        }
        //TODO: else FORBIDDEN
    }

    @Transactional
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(DataNotFoundException::new);
        if(comment.getBlogger().getId().equals(SessionUtil.getBloggerId()))
            commentRepository.deleteById(commentId);
    }
}
