package com.zavialov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zavialov.controller.PostController;
import com.zavialov.repository.PostRepository;
import com.zavialov.repository.PostRepositoryImpl;
import com.zavialov.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepositoryImpl();
    }
}