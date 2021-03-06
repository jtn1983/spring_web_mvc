package ru.tenilin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tenilin.exception.NotFoundException;
import ru.tenilin.model.Post;
import ru.tenilin.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() {
    return service.all();
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable long id) {
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) {
    return service.save(post);
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) {
    service.removeById(id);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handle(NotFoundException e) {
    return "Posts not found";
  }

}
