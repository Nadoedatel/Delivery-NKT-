package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.MyUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUsers, Long> {
    Optional<MyUsers> findByUsername(String username);

}
