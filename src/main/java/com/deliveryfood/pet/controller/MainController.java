package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.models.Shops;
import com.deliveryfood.pet.models.User;
import com.deliveryfood.pet.repo.PostRepository;
import com.deliveryfood.pet.repo.ShopsRepository;
import com.deliveryfood.pet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ShopsRepository shopsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String indexPost(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        Iterable<Shops> shops = shopsRepository.findAll();
        model.addAttribute("shops", shops);
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Optional<MyUsers> users = userRepository.findByUsername(username);

        if (users.isPresent()) {
            model.addAttribute("users", users.get());
            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/catalog")
    public String showCatalog() {
        return "catalog";
    }
}
