package com.skyblog.skyblog.exceptions;


public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("Aucun utilisateur avec l'id : " + id);
    }
}
