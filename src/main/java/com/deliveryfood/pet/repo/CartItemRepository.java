package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {

}

