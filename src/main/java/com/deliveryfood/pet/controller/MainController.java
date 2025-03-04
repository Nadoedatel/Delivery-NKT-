package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String index(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        model.addAttribute("title", "Главная страница");
        return "index";
    }
}
