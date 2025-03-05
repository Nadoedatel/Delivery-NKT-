package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Shops;
import com.deliveryfood.pet.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface usersRepository extends CrudRepository<Users, Long> {
}
