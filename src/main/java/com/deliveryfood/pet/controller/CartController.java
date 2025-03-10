package com.deliveryfood.pet.controller;


import com.deliveryfood.pet.Config.MyUserDetails;
import com.deliveryfood.pet.Service.CartService;
import com.deliveryfood.pet.models.Cart;
import com.deliveryfood.pet.models.CartItems;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.CartItemRepository;
import com.deliveryfood.pet.repo.ProductRepository;
import com.deliveryfood.pet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Authentication authentication) {
        // Получаем объект MyUserDetails, а не MyUsers
        MyUserDetails currentUserDetails = (MyUserDetails) authentication.getPrincipal();

        // Извлекаем объект MyUsers из MyUserDetails
        MyUsers currentUser = currentUserDetails.getMyUser();  // Добавьте метод getMyUser() в MyUserDetails

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        cartService.addToCart(currentUser, product, quantity);

        return "redirect:/cart/view";
    }



    @GetMapping("/view")
    public String viewCart(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";  // Если аутентификация не выполнена, перенаправляем на страницу логина
        }

        MyUserDetails currentUserDetails = (MyUserDetails) authentication.getPrincipal();  // Приводим к MyUserDetails
        if (currentUserDetails == null) {
            return "redirect:/login";  // Если userDetails не найден, перенаправляем на страницу логина
        }

        MyUsers currentUser = currentUserDetails.getMyUser();  // Извлекаем объект MyUsers
        Long userId = currentUser.getId();  // Получаем ID пользователя
        Cart cart = cartService.getCartForUser(userId);  // Передаем ID в сервис
        model.addAttribute("cart", cart);  // Добавляем корзину в модель
        return "cart/view";  // Возвращаем представление корзины
    }


    // Увеличение или уменьшение количества товара
    @PostMapping("/update-quantity")
    public String updateQuantity(@RequestParam Long cartItemId, @RequestParam int quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/cart/view";
    }

    @PostMapping("/remove-item")
    public String removeItem(@RequestParam("cartItemId") Long cartItemId) {
        System.out.println("Удаляем товар с ID: " + cartItemId);
        cartService.removeItemFromCart(cartItemId);;  // Удаление товара
        return "redirect:/cart/view";  // Перенаправление обратно в корзину
    }





}
