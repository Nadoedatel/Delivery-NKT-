package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/add")
    public String showAddProductForm(Model model) {
        return "add-product"; // Имя шаблона для формы добавления товара
    }

    // Обработка данных из формы и сохранение товара
    @PostMapping("/product/add")
    public String addProduct(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam int gram,
            Model model) {
        Product product = new Product(name, price, gram );
        productRepository.save(product);
        return "redirect:/product"; // Перенаправление на страницу со списком товаров
    }

    @GetMapping("/product")
    public String showCatalog(Model model){
        Iterable<Product> product = productRepository.findAll();
        model.addAttribute("product", product);
        return "product";
    }

}
