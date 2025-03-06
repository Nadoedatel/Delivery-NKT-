package com.deliveryfood.pet.controller;


import com.deliveryfood.pet.Service.UserService;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class UserController {

    private UserService service;


    @GetMapping("/{id}")
    public User userByID(@PathVariable int id) {
        return service.userByID(id);
    }

    @PostMapping("/registration")
    public String addUser(@RequestBody MyUsers users){
        service.addUser(users);
        return "User is saved";
    };
}
