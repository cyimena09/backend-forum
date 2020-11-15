package com.skyblog.skyblog.exceptions;


public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(Long id) {
        super("Aucun utilisateur avec l'id : " + id);
    }
}