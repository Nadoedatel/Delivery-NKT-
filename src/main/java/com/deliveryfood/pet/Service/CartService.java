package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.models.Cart;
import com.deliveryfood.pet.models.CartItems;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.CartItemRepository;
import com.deliveryfood.pet.repo.CartRepository;
import com.deliveryfood.pet.repo.ProductRepository;
import com.deliveryfood.pet.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItems> getCartItems(Long userId) {
        return cartItemRepository.findByCartUserId(userId);
    }

    // Метод для добавления товара в корзину
    public void addToCart(MyUsers user, Product product, int quantity) {
        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        Optional<CartItems> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
            cartItemRepository.save(existingItem.get());
        } else {
            CartItems cartItem = new CartItems(cart, product, quantity);
            cart.getItems().add(cartItem);
            cartRepository.save(cart);
        }
    }


    // Получение корзины пользователя, либо создание новой
    private Cart getOrCreateCart(Long userId) {
        // Ищем корзину по идентификатору пользователя
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            // Если корзины нет, создаем новую и связываем с пользователем
            cart = new Cart();
            MyUsers user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));  // Находим пользователя по ID
            cart.setUser(user);
            cartRepository.save(cart);  // Сохраняем новую корзину
        }
        return cart;
    }


    // В вашем сервисе (например, CartService)
    public Cart getCartForUser(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            // Инициализируем коллекцию вручную
            cart.getItems().size();  // Пример инициализации
        }
        return cart;
    }

    public void updateQuantity(Long cartItemId, int quantity) {
        CartItems cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Товар в корзине не найден"));

        if (quantity > 0) {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        } else {
            cartItemRepository.delete(cartItem);
        }
    }

    @Transactional
    public void removeItemFromCart(Long cartItemId) {
        cartItemRepository.deleteByCartItemId(cartItemId);  // Удаление товара из корзины
    }
    @Transactional
    public void clearCart(Long userId) {
        cartItemRepository.deleteAllByUserId(userId);
    }
}
