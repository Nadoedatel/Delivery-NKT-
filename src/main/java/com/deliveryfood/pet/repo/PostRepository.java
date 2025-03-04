package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}