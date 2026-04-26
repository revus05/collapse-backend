package com.example.collapse.controller;

import com.example.collapse.config.JwtUserPrincipal;
import com.example.collapse.dto.order.CreateOrderRequestDTO;
import com.example.collapse.dto.order.OrderDTO;
import com.example.collapse.dto.order.UpdateOrderStatusRequestDTO;
import com.example.collapse.dto.response.Response;
import com.example.collapse.service.OrderService;
import com.example.collapse.swagger.order.CreateOrderOperation;
import com.example.collapse.swagger.order.DeleteOrderOperation;
import com.example.collapse.swagger.order.GetAllOrdersOperation;
import com.example.collapse.swagger.order.UpdateOrderStatusOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "Заказ", description = "Управление заказами")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @CreateOrderOperation
    @PostMapping()
    public Response createOrder(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        OrderDTO createdOrder = orderService.createOrder(principal.uuid(), createOrderRequestDTO);
        return new Response("Заказ успешно создан", HttpStatus.CREATED, createdOrder);
    }

    @GetAllOrdersOperation
    @GetMapping("/admin")
    public Response getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrdersForAdmin();
        return new Response("Список заказов получен", HttpStatus.OK, orders);
    }

    @UpdateOrderStatusOperation
    @PatchMapping("/admin/{uuid}/status")
    public Response updateOrderStatus(
            @PathVariable String uuid,
            @Valid @RequestBody UpdateOrderStatusRequestDTO body) {
        OrderDTO updated = orderService.updateStatus(uuid, body.getStatus());
        return new Response("Статус заказа изменён", HttpStatus.OK, updated);
    }

    @DeleteOrderOperation
    @DeleteMapping("/admin/{uuid}")
    public Response deleteOrder(@PathVariable String uuid) {
        orderService.deleteOrder(uuid);
        return new Response("Заказ удалён", HttpStatus.OK, null);
    }
}
