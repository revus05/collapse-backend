package com.example.collapse.controller;

import com.example.collapse.config.JwtUserPrincipal;
import com.example.collapse.dto.order.CreateOrderRequestDTO;
import com.example.collapse.dto.order.OrderDTO;
import com.example.collapse.dto.response.Response;
import com.example.collapse.service.OrderService;
import com.example.collapse.swagger.order.CreateOrderOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
}
