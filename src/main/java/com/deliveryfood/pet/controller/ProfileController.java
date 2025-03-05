package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.models.Users;
import com.deliveryfood.pet.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable(value = "id") long id, Model model){
        Optional<Users> users = usersRepository.findById(id);
        model.addAttribute("users", users);
        return "profile";
    }
}
