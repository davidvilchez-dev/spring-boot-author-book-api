package com.david.springboot.springboot_author_book_api.dto;

import java.util.Date;

public class BookRequest {

    private String title;
    private String gender;
    private Date published;
    private Long authorId;

    public BookRequest() {

    }

    public BookRequest(String title, String gender, Date published, Long authorId) {
        this.title = title;
        this.gender = gender;
        this.published = published;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

}
