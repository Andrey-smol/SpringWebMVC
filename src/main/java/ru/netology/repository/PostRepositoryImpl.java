package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, Post> repository = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    @Override
    public List<Post> all() {
        if (repository.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Post> getById(long id) {
        if (repository.containsKey(id)) {
            return Optional.of(repository.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Post> save(Post post) {
        String content = post.getContent();
        long idPost = post.getId();
        //Если от клиента приходит пост с id=0, значит, это создание нового поста.
        if (idPost == 0) {
            id.incrementAndGet();
            repository.put(id.get(), new Post(id.get(), content));
            return Optional.of(new Post(id.get(), content));
        }
        //Если от клиента приходит пост с id !=0, значит, это сохранение (обновление) существующего поста.
        if (repository.containsKey(idPost)) {
            Post p = repository.get(idPost);
            p.setContent(post.getContent());
            return Optional.of(new Post(idPost, content));
        }
        return Optional.empty();
    }

    @Override
    public boolean removeById(long id) {
        return (repository.remove(id) != null);
    }
}
