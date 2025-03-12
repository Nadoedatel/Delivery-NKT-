package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.Config.MyUserDetails;
import com.deliveryfood.pet.Service.OrderService;
import com.deliveryfood.pet.Service.UserService;
import com.deliveryfood.pet.models.Cart;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Orders;
import com.deliveryfood.pet.models.Post;
import com.deliveryfood.pet.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    // GET-метод для отображения страницы с формой
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        MyUsers currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

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
        MyUsers currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        Orders order = orderService.createOrder(currentUser.getId(), address, paymentMethod);

        redirectAttributes.addFlashAttribute("successMessage", "Заказ успешно создан!");

        return "redirect:/";
    }

    // Метод для получения текущего пользователя
    private MyUsers getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            return ((MyUserDetails) principal).getMyUser();
        }
        return null;
    }


    @GetMapping("/historyOrders")
    public String getUserOrders(Authentication authentication, Model model) {
        // Получаем текущего пользователя
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        // Получаем ID пользователя
        MyUsers currentUser = userDetails.getMyUser();  // Извлекаем объект MyUsers
        Long userId = currentUser.getId();

        // Получаем заказы пользователя
        List<Orders> orders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", orders);
        return "cart/orders/historyOrders";
    }

    @GetMapping("/historyOrders-details")
    public String getOrderDetails(@RequestParam Long orderId, Model model) {
        Orders orders = orderService.getOrderDetails(orderId);
        if (orders == null) {
            throw new RuntimeException("Заказ не найден");
        }
        model.addAttribute("orders", orders);
        return "cart/orders/historyOrders-details";
    }
}

