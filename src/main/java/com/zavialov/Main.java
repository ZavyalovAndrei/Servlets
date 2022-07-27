package com.zavialov;

import com.zavialov.controller.PostController;
import com.zavialov.service.PostService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext("com.zavialov");
        final var controller = context.getBean("postController");
        final var isSameController = controller == context.getBean(PostController.class);
        final var service = context.getBean("postService");
        final var isSameService = service == context.getBean(PostService.class);

    }
}
