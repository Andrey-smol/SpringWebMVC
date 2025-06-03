package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {

        this.repository = repository;
    }

    public List<Post> all() {

        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id)
                .orElseThrow(() -> new NotFoundException("The post with this ID was not found"));
    }

    public Post save(Post post) {
        return repository.save(post).orElseThrow(() -> new NotFoundException("this Post was not saved"));
    }

    public void removeById(long id) {
        if (!repository.removeById(id)) {
            throw new NotFoundException("The post with the " + id + " was not deleted");
        }
    }
}
