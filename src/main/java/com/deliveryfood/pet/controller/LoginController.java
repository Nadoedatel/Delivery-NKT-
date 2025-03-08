package com.deliveryfood.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Если есть сообщение об успешной регистрации, оно будет автоматически добавлено в модель
        return "login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
