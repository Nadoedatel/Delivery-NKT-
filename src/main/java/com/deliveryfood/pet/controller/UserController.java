package com.deliveryfood.pet.controller;


import com.deliveryfood.pet.Service.UserService;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private UserService service;


    @GetMapping("/profile/{id}")
    public User userByID(@PathVariable int id) {
        return service.userByID(id);
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String name,
                          @RequestParam String lastname,
                          @RequestParam String email,
                          @RequestParam(required = false) String phone,
                          @RequestParam(required = false) String address,
                          RedirectAttributes redirectAttributes) {

        MyUsers newUser = new MyUsers();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setAddress(address);


        redirectAttributes.addFlashAttribute("successMessage", "Регистрация прошла успешно!");
        service.addUser(newUser);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String showRegistration() {
        return "registration";
    }

    ;
}
