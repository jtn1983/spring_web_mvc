package ru.tenilin.service;

import org.springframework.stereotype.Service;
import ru.tenilin.exception.NotFoundException;
import ru.tenilin.model.Post;
import ru.tenilin.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
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

