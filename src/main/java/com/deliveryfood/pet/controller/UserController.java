package com.deliveryfood.pet.controller;


import com.deliveryfood.pet.Service.UserService;
import com.deliveryfood.pet.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private UserService service;


    @GetMapping("/{id}")
    public User userByID(@PathVariable int id) {
        return service.userByID(id);
    }

}
