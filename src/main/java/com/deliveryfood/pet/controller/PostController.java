package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.Service.PostService;
import com.deliveryfood.pet.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/product/{id}")
    public String getShopsProduct(@PathVariable Long id, Model model) {
        List<Product> products = postService.getProductByShopId(id);
        model.addAttribute("products", products);
        return "product";
    }
}
