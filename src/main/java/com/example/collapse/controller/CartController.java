package com.example.collapse.controller;

import com.example.collapse.dto.cart.AddToCartRequestDTO;
import com.example.collapse.dto.cart.CartItemDTO;
import com.example.collapse.dto.response.Response;
import com.example.collapse.service.CartService;
import com.example.collapse.swagger.cart.AddToCartOperation;
import com.example.collapse.swagger.cart.GetCartOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cart")
@Tag(name = "Корзина", description = "Управление корзиной пользователя")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @AddToCartOperation
    @PostMapping()
    public Response addToCart(@Valid @RequestBody AddToCartRequestDTO addToCartRequestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CartItemDTO cartItemDTO = cartService.addToCart(((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername(), addToCartRequestDTO);
        return new Response("Товар успешно добавлен в корзину", HttpStatus.OK, cartItemDTO);
    }

    @GetCartOperation
    @GetMapping()
    public Response getCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<CartItemDTO> cart = cartService.getCart(((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername());
        return new Response("Корзина товаров успешно получена", HttpStatus.OK, cart);
    }
}