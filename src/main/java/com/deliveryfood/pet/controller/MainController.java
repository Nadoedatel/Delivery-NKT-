package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.models.Shops;
import com.deliveryfood.pet.models.Users;
import com.deliveryfood.pet.repo.PostRepository;
import com.deliveryfood.pet.repo.ShopsRepository;
import com.deliveryfood.pet.repo.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("title", "Профиль пользователя");
        return "profile";
    }
}
