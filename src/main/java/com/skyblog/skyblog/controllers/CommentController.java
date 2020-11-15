package com.skyblog.skyblog.controllers;

import com.skyblog.skyblog.exceptions.CommentNotFoundException;
import com.skyblog.skyblog.exceptions.TopicNotFoundException;
import com.skyblog.skyblog.models.Comment;
import com.skyblog.skyblog.repositories.CommentRepository;
import com.skyblog.skyblog.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TopicRepository topicRepository;


    @GetMapping("/topics/{topicId}/comments")
    public Page<Comment> getAllCommentByTopic(@PathVariable Long topicId, Pageable pageable){
        return commentRepository.findByTopicId(topicId, pageable);
    }

    @PostMapping("/topics/{topicId}/comments")
    public Comment createComment(@PathVariable Long topicId,
                                 @RequestBody Comment comment){
        return topicRepository.findById(topicId).map(p -> {
            comment.setTopic(p);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new TopicNotFoundException(topicId));
    }

    @PutMapping("/topics/{topicId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(value = "topicId") Long topicId,
                                 @PathVariable(value = "commentId") Long commentId,
                                 @RequestBody Comment comment){

        //check if topic exist before check if comment exist
        if(!topicRepository.existsById(topicId)){
            throw new TopicNotFoundException(topicId);
        }

        return commentRepository.findById(commentId).map(c -> {
            c.setText(comment.getText());
            return commentRepository.save(c);
        }).orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    @DeleteMapping("/topics/{topicId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "topicId") Long topicId,
                                           @PathVariable(value = "commentId") Long commentId){
        return commentRepository.findByIdAndTopicId(commentId, topicId).map(c -> {
            commentRepository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new CommentNotFoundException(commentId));
    }
}
