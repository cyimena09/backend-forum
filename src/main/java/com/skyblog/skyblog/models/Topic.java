package com.skyblog.skyblog.models;

import javax.persistence.*;


@Entity
@Table(name = "Topics")
public class Topic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "topic_id", length = 36, updatable = false, nullable = false)
        private Long id;

        @Column(columnDefinition = "varchar(255)")
        private String title;

        @Column(columnDefinition = "varchar(255)")
        private String description;

        @Column(columnDefinition = "text")
        private String content;


//  ************************************   GETTER & SETTER ****************************************************

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }
