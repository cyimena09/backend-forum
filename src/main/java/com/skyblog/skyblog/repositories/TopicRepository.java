package com.skyblog.skyblog.repositories;

import com.skyblog.skyblog.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TopicRepository  extends JpaRepository<Topic, Long> {
}