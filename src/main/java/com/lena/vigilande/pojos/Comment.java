package com.lena.vigilande.pojos;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Comment {
    private String author;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    private String address;
    private String comment;

    public Comment(String author, String address, String comment) {
        this.author = author;
        this.address = address;
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
}
