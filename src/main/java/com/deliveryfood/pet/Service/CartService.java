package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.models.Cart;
import com.deliveryfood.pet.models.CartItems;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Product;
import com.deliveryfood.pet.repo.CartRepository;
import com.deliveryfood.pet.repo.ProductRepository;
import com.deliveryfood.pet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Метод для добавления товара в корзину
    public void addToCart(MyUsers user, Product product, int quantity) {
        Long userId = user.getId();  // Получаем идентификатор пользователя
        Cart cart = cartRepository.findByUserId(userId);  // Ищем корзину по идентификатору пользователя
        if (cart == null) {
            cart = new Cart();  // Если корзины нет, создаем новую
            cart.setUser(user);  // Привязываем корзину к пользователю
            cartRepository.save(cart);
        }

        // Создаем CartItems с нужными параметрами
        CartItems cartItem = new CartItems(cart, product, quantity);

        // Добавляем CartItems в корзину
        cart.addItem(product, quantity);   // Теперь addItem принимает CartItems
        cartRepository.save(cart);  // Сохраняем обновленную корзину
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

}
