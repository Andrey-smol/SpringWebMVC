package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.PostDTO;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    @Autowired
    public PostController(PostService service) {

        this.service = service;
    }

    @GetMapping
    public List<PostDTO> all() {

        return service.all();
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable long id) {

        return service.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO save(@RequestBody PostDTO post) {

        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {

        service.removeById(id);
    }
}
