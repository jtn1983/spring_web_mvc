package ru.tenilin.repository;

import org.springframework.stereotype.Repository;
import ru.tenilin.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
  private ConcurrentHashMap<Long, Post> postsList = new ConcurrentHashMap<>();
  private AtomicLong idPost = new AtomicLong(2);

  public PostRepository(){
    postsList.put(1L, new Post(1, "Hello1"));
    postsList.put(2L, new Post(2, "Hello2"));
  }

  public List<Post> all() {
    return new ArrayList<>(postsList.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postsList.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      Post newPost = new Post(idPost.incrementAndGet(), post.getContent());
      postsList.put(idPost.get(), newPost);
      return newPost;
    } else {
      postsList.put(post.getId(), post);
    }
    return post;
  }

  public void removeById(long id) {
    postsList.remove(id);
  }
}