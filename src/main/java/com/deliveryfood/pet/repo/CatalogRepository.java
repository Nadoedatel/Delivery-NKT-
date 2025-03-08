package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
