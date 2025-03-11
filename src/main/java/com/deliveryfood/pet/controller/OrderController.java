package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.Config.MyUserDetails;
import com.deliveryfood.pet.Service.OrderService;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Orders;
import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;
    // GET-метод для отображения страницы с формой
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        // Получаем текущего пользователя
        MyUsers currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login"; // Перенаправляем на страницу входа, если пользователь не авторизован
        }

        // Передаем userId в модель
        model.addAttribute("userId", currentUser.getId());
        return "cart/orders/create";
    }

    // POST-метод для обработки данных формы
    @PostMapping("/create")
    public String createOrder(
            @RequestParam String address,
            @RequestParam String paymentMethod,
            RedirectAttributes redirectAttributes
    ) {
        // Получаем текущего пользователя
        MyUsers currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login"; // Перенаправляем на страницу входа, если пользователь не авторизован
        }

        // Создаем заказ
        Orders order = orderService.createOrder(currentUser.getId(), address, paymentMethod);

        // Добавляем flash-атрибут с сообщением об успехе
        redirectAttributes.addFlashAttribute("successMessage", "Заказ успешно создан!");

        // Перенаправляем на главную страницу
        return "redirect:/";
    }

    // Метод для получения текущего пользователя
    private MyUsers getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            return ((MyUserDetails) principal).getMyUser();
        }
        return null; // Если пользователь не авторизован
    }
}

