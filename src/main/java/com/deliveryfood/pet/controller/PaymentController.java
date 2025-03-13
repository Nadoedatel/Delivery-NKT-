package com.deliveryfood.pet.controller;

import com.deliveryfood.pet.Service.PaymentService;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.PaymentCard;
import com.deliveryfood.pet.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addCard(@RequestParam String cardNumber,
                          @RequestParam String expiryDate,
                          @RequestParam String cvv,
                          Principal principal) {
        // Получаем userId из аутентифицированного пользователя
        MyUsers user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Long userId = user.getId();

        // Создаем объект карты
        PaymentCard card = new PaymentCard();
        card.setCardNumber(cardNumber);
        card.setExpiryDate(expiryDate);
        card.setCvv(cvv);

        // Добавляем карту
        paymentService.addCard(userId, card);

        // Перенаправляем пользователя на страницу управления картами
        return "redirect:/payment";
    }

    @GetMapping
    public String getPaymentPage(Model model, Principal principal) {
        // Получаем userId из аутентифицированного пользователя
        MyUsers user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Long userId = user.getId();

        // Получаем список карт пользователя
        List<PaymentCard> cards = paymentService.getCardsByUser(userId);

        // Передаем список карт в модель
        model.addAttribute("cards", cards);

        return "payment";
    }

    @Transactional
    @PostMapping("/delete/{cardId}")
    public String deleteCard(@PathVariable Long cardId, Principal principal) {
        // Получаем userId из аутентифицированного пользователя
        MyUsers user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Long userId = user.getId();

        // Удаляем карту
        paymentService.deleteCard(cardId, userId);

        // Перенаправляем пользователя на страницу управления картами
        return "redirect:/payment";
    }
}
