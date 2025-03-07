package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductByShopId(Long storeId) {
        return productRepository.findByCatalog_Post_Id(storeId);
    }
}
