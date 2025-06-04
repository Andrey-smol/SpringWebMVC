package ru.netology.model;

import java.util.Objects;

public class PostEntity {
    private long id;
    private String content;
    private boolean removed = false;

    public PostEntity() {
    }

    public PostEntity(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        PostEntity post = (PostEntity) o;
        return id == post.id && removed == post.removed && Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, removed, content);
    }
}
