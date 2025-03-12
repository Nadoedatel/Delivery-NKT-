package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o JOIN FETCH o.orderItems WHERE o.user.id = :userId")
    List<Orders> findByUserId(Long userId);
}
