package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.PostEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, PostEntity> repository = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    @Override
    public List<PostEntity> all() {
        if (repository.isEmpty()) {
            return Collections.emptyList();
        }
        return repository.values().stream().filter(p-> !p.isRemoved()).collect(Collectors.toList());
    }

    @Override
    public Optional<PostEntity> getById(long id) {
        PostEntity post = getPostById(id);
        return (post == null) ? Optional.empty() : Optional.of(post);
    }

    @Override
    public Optional<PostEntity> save(PostEntity post) {
        String content = post.getContent();
        long idPost = post.getId();
        //Если от клиента приходит пост с id=0, значит, это создание нового поста.
        if (idPost == 0) {
            id.incrementAndGet();
            repository.put(id.get(), new PostEntity(id.get(), content));
            return Optional.of(new PostEntity(id.get(), content));
        }
        //Если от клиента приходит пост с id !=0, значит, это сохранение (обновление) существующего поста.
        PostEntity p = getPostById(idPost);
        if(p != null){
            p.setContent(post.getContent());
            return Optional.of(new PostEntity(idPost, content));
        }
        return Optional.empty();
    }

    @Override
    public boolean removeById(long id) {
        PostEntity post = getPostById(id);
        if (post == null) {
            return false;
        }
        post.setRemoved(true);
        return true;
    }
    private PostEntity getPostById(long id){
        PostEntity post = repository.get(id);
        if (post == null || post.isRemoved()) {
            return null;
        }
        return post;
    }
}
