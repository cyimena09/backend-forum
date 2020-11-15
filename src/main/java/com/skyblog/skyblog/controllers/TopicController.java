package com.skyblog.skyblog.controllers;

import com.skyblog.skyblog.exceptions.TopicNotFoundException;
import com.skyblog.skyblog.models.Topic;
import com.skyblog.skyblog.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class TopicController {

        @Autowired
        private TopicRepository topicRepository;

        @GetMapping("/topics")
        public Page<Topic> getAllTopics(Pageable pageable){
            return topicRepository.findAll(pageable);
        }

        @PostMapping("/topics")
        public Topic createTopic(@RequestBody Topic topic){
            return topicRepository.save(topic);
        }

        @PutMapping("/topics/{topicId}")
        public Topic updateTopic(@PathVariable Long topicId, @RequestBody Topic topic){
            return topicRepository.findById(topicId).map(p -> {
                p.setTitle(topic.getTitle());
                p.setDescription(topic.getDescription());
                p.setContent(topic.getContent());
                return topicRepository.save(p);
            }).orElseThrow(() -> new TopicNotFoundException(topicId));
        }

        @DeleteMapping("/topic/{topicId}")
        public ResponseEntity<?> deleteTopic(@PathVariable Long topicId){
            return topicRepository.findById(topicId).map(p -> {
                topicRepository.delete(p);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new TopicNotFoundException(topicId));
        }
}
