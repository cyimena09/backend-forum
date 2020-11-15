package com.skyblog.skyblog.repositories;

import com.skyblog.skyblog.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByTopicId(Long id, Pageable pageable);

    Optional<Comment> findByIdAndTopicId(Long commentId, Long topicId);
}
