package com.example.collapse.service;

import com.example.collapse.dto.order.CreateOrderRequestDTO;
import com.example.collapse.dto.order.OrderDTO;
import com.example.collapse.entity.CartItem;
import com.example.collapse.entity.Order;
import com.example.collapse.entity.User;
import com.example.collapse.enums.OrderStatus;
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

        Order order = new Order(
                user,
                cartItemList,
                createOrderRequestDTO.getPhone(),
                createOrderRequestDTO.getAddress(),
                createOrderRequestDTO.getComment());

        Order createdOrder = orderRepository.save(order);

        StringBuilder message = new StringBuilder();
        message.append("<b>Новый заказ!</b>\n")
                .append("ID: <code>").append(createdOrder.getUuid()).append("</code>\n")
                .append("Имя: ").append(createdOrder.getUser().getFirstName()).append("\n")
                .append("Телефон: ").append(createdOrder.getPhone()).append("\n")
                .append("Адрес: ").append(createdOrder.getAddress()).append("\n")
                .append("Сумма: ").append(createdOrder.getTotalAmount()).append(" ").append(createdOrder.getCurrency());

        if (createdOrder.getComment() != null && !createdOrder.getComment().isBlank()) {
            message.append("\nКомментарий: ").append(createdOrder.getComment());
        }

        telegramService.sendOrderNotification(message.toString());

        return new OrderDTO(createdOrder);
    }

    @Transactional
    public List<OrderDTO> getAllOrdersForAdmin() {
        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(OrderDTO::new)
                .toList();
    }

    @Transactional
    public OrderDTO updateStatus(String orderUuid, OrderStatus status) {
        Order order = orderRepository.findById(orderUuid)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        order.setStatus(status);
        Order saved = orderRepository.save(order);
        return new OrderDTO(saved);
    }

    @Transactional
    public void deleteOrder(String orderUuid) {
        Order order = orderRepository.findById(orderUuid)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        orderRepository.delete(order);
    }
}
