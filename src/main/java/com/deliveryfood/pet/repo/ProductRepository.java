package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN p.catalog c WHERE c.post.id = :postId")
    List<Product> findByCatalog_Post_Id(@Param("postId") Long postId);
}
