package ru.netology.model;

public class PostDTO {
    private long id;
    private String content;

    public PostDTO() {
    }

    public PostDTO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
