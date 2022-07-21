package com.zavialov.servlet;

import com.zavialov.controller.PostController;
import com.zavialov.exception.NotFoundException;
import com.zavialov.repository.PostRepository;
import com.zavialov.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            final long id;
            final var postsPath = "/api/posts";
            final var pathMatch = postsPath + "/\\d+";
            if (method.equals("GET") && path.equals(postsPath)) {
                controller.all(resp);
                return;
            }

            if (method.equals("GET") && path.matches(pathMatch)) {
                id = getID(path);
                controller.getById(id, resp);
                return;
            }
            if (method.equals("POST") && path.equals(postsPath)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals("DELETE") && path.matches(pathMatch)) {
                id = getID(path);
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private long getID(String path) {
        return Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
    }
}