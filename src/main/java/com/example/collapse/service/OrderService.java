package com.example.collapse.service;

import com.example.collapse.dto.order.CreateOrderRequestDTO;
import com.example.collapse.dto.order.OrderDTO;
import com.example.collapse.entity.CartItem;
import com.example.collapse.entity.Order;
import com.example.collapse.entity.User;
import com.example.collapse.repository.CartRepository;
import com.example.collapse.repository.OrderRepository;
import com.example.collapse.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final TelegramNotificationService telegramService;

    @Transactional
    public OrderDTO createOrder(String userUuid, CreateOrderRequestDTO createOrderRequestDTO) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (createOrderRequestDTO.getOrderItemsUuids().isEmpty()) {
            throw new RuntimeException("Нельзя создать заказ без товаров");
        }

        List<CartItem> cartItemList = cartRepository.findAllByUuidIn(createOrderRequestDTO.getOrderItemsUuids());

        Order order = new Order(user, cartItemList);

        Order createdOrder = orderRepository.save(order);

        telegramService.sendOrderNotification(
                "<b>Новый заказ!</b>\n" +
                        "ID: <code>" + createdOrder.getUuid() + "</code>\n" +
                        "Имя: " + createdOrder.getUser().getFirstName() + "\n" +
                        "Сумма: " + createdOrder.getTotalAmount() + " " + createdOrder.getCurrency()
        );

        return new OrderDTO(createdOrder);
    }
}
