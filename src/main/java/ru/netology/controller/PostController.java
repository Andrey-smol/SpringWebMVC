package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public void all(HttpServletResponse response) throws IOException {
        final var data = service.all();
        sendResponseDataJson(data, response);
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable long id, HttpServletResponse response) throws IOException {
        try {
            final  var data = service.getById(id);
            sendResponseDataJson(data, response);
        } catch (NotFoundException n) {
            sendResponseDataJson("The post with this ID was not found", response);
        }
    }

    @PostMapping
    public void save(Reader body, HttpServletResponse response) throws IOException{
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        try {
            final var data = service.save(post);
            sendResponseDataJson(data, response);
        } catch (NotFoundException n) {
            sendResponseDataJson(n.getMessage(), response);
        }
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id, HttpServletResponse response) throws IOException{
        try {
            service.removeById(id);
            sendResponseDataJson("The post was deleted successfully", response);
        } catch (NotFoundException n) {
            sendResponseDataJson(n.getMessage(), response);
        }
    }

    private void sendResponseDataJson(Object o, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(o));
    }
}
