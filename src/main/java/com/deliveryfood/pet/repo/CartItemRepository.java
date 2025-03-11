package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.CartItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartUserId(Long userId);
    @Modifying
    @Query("DELETE FROM CartItems ci WHERE ci.cart.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItems ci WHERE ci.id = :cartItemId")
    void deleteByCartItemId(Long cartItemId);

}

