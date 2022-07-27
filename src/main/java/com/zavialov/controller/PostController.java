package com.zavialov.controller;

import com.google.gson.Gson;
import com.zavialov.model.Post;
import com.zavialov.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    if (!service.all().isEmpty()) {
      final var data = service.all();
      response.getWriter().print(gson.toJson(data));
    } else {
      response.getWriter().print(gson.toJson("Repository is empty"));
    }
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.getById(id);
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    String data = "ID " + id + " not found.";
    if (service.getById(id) != null) {
      service.removeById(id);
      data = "ID " + id + " successfully removed.";
    }
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }
}