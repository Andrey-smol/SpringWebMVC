package ru.netology.mapperDTO;

import org.springframework.stereotype.Component;
import ru.netology.model.PostDTO;
import ru.netology.model.PostEntity;

@Component
public class Mapper {
    public PostDTO toDto(PostEntity post) {
        return new PostDTO(post.getId(), post.getContent());
    }

    public PostEntity toEntity(PostDTO postDTO) {
        return new PostEntity(postDTO.getId(), postDTO.getContent());
    }
}
