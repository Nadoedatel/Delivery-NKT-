package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.models.Catalog;
import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.CatalogRepository;
import com.deliveryfood.pet.repo.PostRepository;
import com.deliveryfood.pet.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PostRepository postRepository;

    public void addProductToPost(Long productId, Long postId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Магазин не найден"));

        Catalog catalog = new Catalog();
        catalog.setProduct(product);
        catalog.setPost(post);
        catalogRepository.save(catalog);
    }
}
