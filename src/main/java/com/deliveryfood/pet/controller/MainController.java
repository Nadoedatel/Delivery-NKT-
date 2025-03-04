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
    public String indexPost(Model model){
        Iterable<Post> posts = postRepository.findAll();
        posts.forEach(post -> System.out.println("work"));
        model.addAttribute("posts", posts);
        return "index";
    }
}
