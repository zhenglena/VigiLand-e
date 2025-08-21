package com.lena.vigilande.dtos;

/**
 * Forms the request body for the POST endpoint.
 */
public class CommentsRequest {
    private String comment;
    private String author;

    public CommentsRequest(String comment, String author) {
        this.comment = comment;
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
