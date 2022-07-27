
package com.zavialov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.zavialov.config.JavaConfig;
import com.zavialov.service.PostService;

public class Main {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(JavaConfig.class);
        final var controller = context.getBean("postController");
        final var service = context.getBean(PostService.class);
        final var isSame = service == context.getBean("postService");
    }
}