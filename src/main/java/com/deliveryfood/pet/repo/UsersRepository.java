package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
