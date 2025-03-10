package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.CartItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItems ci WHERE ci.id = :cartItemId")
    void deleteByCartItemId(Long cartItemId);

}

