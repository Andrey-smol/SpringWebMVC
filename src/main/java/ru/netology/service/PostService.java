package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.mapperDTO.Mapper;
import ru.netology.model.PostDTO;
import ru.netology.model.PostEntity;
import ru.netology.repository.PostRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    private final PostRepository repository;
    private final Mapper mapper;

    @Autowired
    public PostService(PostRepository repository, Mapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PostDTO> all() {

        return repository.all().stream().map(mapper::toDto).collect(toList());
    }

    public PostDTO getById(long id) {
        PostEntity postEntity = repository.getById(id)
                .orElseThrow(() -> new NotFoundException("The post with this ID was not found"));
        return mapper.toDto(postEntity);
    }

    public PostDTO save(PostDTO post) {
        PostEntity postEntity = repository
                .save(mapper.toEntity(post))
                .orElseThrow(() -> new NotFoundException("this Post was not saved"));
        return mapper.toDto(postEntity);
    }

    public void removeById(long id) {
        if (!repository.removeById(id)) {
            throw new NotFoundException("The post with the " + id + " was not deleted");
        }
    }
}
