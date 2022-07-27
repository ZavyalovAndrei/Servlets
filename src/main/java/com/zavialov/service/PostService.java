package com.zavialov.service;

import com.zavialov.exception.NotFoundException;
import com.zavialov.model.Post;
import com.zavialov.repository.PostRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepositoryImpl repository;

    public PostService(PostRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}