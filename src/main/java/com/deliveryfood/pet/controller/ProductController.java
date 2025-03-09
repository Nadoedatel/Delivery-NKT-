package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.Service.CatalogService;
import com.deliveryfood.pet.Service.PostService;
import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.CatalogRepository;
import com.deliveryfood.pet.repo.PostRepository;
import com.deliveryfood.pet.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private PostService postService;

    @GetMapping("/product/{id}/add")
    public String showAddProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("postId", id); // Передаем ID магазина в модель
        return "add-product"; // Имя шаблона для формы добавления товара
    }

    // Обработка данных из формы и сохранение товара
    @PostMapping("/product/add")
    public String addProduct(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam int gram,
            @RequestParam String img,
            @RequestParam Long postId, // Получаем ID магазина из формы
            Model model) {
        Product product = new Product(name, price, gram, img);
        productRepository.save(product);

        // Добавляем связь между товаром и магазином
        catalogService.addProductToPost(product.getId(), postId);

        return "redirect:/product/" + postId; // Перенаправление на страницу магазина
    }

    @GetMapping("/product/{id}")
    public String showCatalog(@PathVariable Long id, Model model) {
        List<Product> products = postService.getProductByPostId(id); // Используем PostService
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Магазин не найден"));
        model.addAttribute("products", products);
        model.addAttribute("post", post); // Передаем объект post в модель
        return "product"; // Имя шаблона (product.html)
    }

}
