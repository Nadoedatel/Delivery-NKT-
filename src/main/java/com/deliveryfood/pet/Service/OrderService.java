package com.deliveryfood.pet.Service;


import com.deliveryfood.pet.models.CartItems;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.Orders;
import com.deliveryfood.pet.models.OrderItem;
import com.deliveryfood.pet.repo.CartItemRepository;
import com.deliveryfood.pet.repo.OrderRepository;
import com.deliveryfood.pet.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    public Orders createOrder(Long userId, String address, String paymentMethod) {
        MyUsers user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItems> cartItems = cartService.getCartItems(userId);

        Orders order = new Orders();
        order.setUser(user);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(paymentMethod);
        order.setStatus("Оформлен");
        order.setCreatedAt(new Date());

        double totalPrice = 0;
        for (CartItems cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(order); // Устанавливаем связь с заказом
            orderItem.setProduct(cartItem.getProduct().getName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            order.getOrderItems().add(orderItem);

            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);

        // Сохраняем заказ
        Orders savedOrder = orderRepository.save(order);

        cartService.clearCart(userId);
        return savedOrder;
    }
    @Transactional
    public List<Orders> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Orders getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));
    }
}
