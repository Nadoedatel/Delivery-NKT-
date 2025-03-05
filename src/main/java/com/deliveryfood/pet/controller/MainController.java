package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.models.Shops;
import com.deliveryfood.pet.repo.PostRepository;
import com.deliveryfood.pet.repo.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ShopsRepository shopsRepository;


    @GetMapping("/")
    public String indexPost(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        Iterable<Shops> shops = shopsRepository.findAll();
        model.addAttribute("shops", shops);
        return "index";
    }
}
