package com.lena.vigilande.dtos;

public class CommentsRequest {
    private String address;
    private String comment;
    private String author;

    public CommentsRequest(String address, String comment, String author) {
        this.address = address;
        this.comment = comment;
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
