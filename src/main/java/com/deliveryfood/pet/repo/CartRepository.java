package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Cart;
import com.deliveryfood.pet.models.CartItems;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

}
