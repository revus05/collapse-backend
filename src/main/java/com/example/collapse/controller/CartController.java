package com.example.collapse.controller;

import com.example.collapse.config.JwtUserPrincipal;
import com.example.collapse.dto.cart.AddToCartRequestDTO;
import com.example.collapse.dto.cart.CartItemDTO;
import com.example.collapse.dto.cart.UpdateCartItemRequestDTO;
import com.example.collapse.dto.response.Response;
import com.example.collapse.service.CartService;
import com.example.collapse.swagger.cart.AddToCartOperation;
import com.example.collapse.swagger.cart.DeleteCartItemOperation;
import com.example.collapse.swagger.cart.GetCartOperation;
import com.example.collapse.swagger.cart.UpdateCartItemOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "Корзина", description = "Управление корзиной пользователя")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @AddToCartOperation
    @PostMapping()
    public Response addToCart(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @Valid @RequestBody AddToCartRequestDTO addToCartRequestDTO) {
        CartItemDTO cartItemDTO = cartService.addToCart(principal.uuid(), addToCartRequestDTO);
        return new Response("Товар успешно добавлен в корзину", HttpStatus.OK, cartItemDTO);
    }

    @GetCartOperation
    @GetMapping()
    public Response getCart(@AuthenticationPrincipal JwtUserPrincipal principal) {
        List<CartItemDTO> cart = cartService.getCart(principal.uuid());
        return new Response("Корзина товаров успешно получена", HttpStatus.OK, cart);
    }

    @UpdateCartItemOperation
    @PatchMapping("/{uuid}")
    public Response updateCartItem(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @PathVariable String uuid,
            @Valid @RequestBody UpdateCartItemRequestDTO body) {
        CartItemDTO updated = cartService.updateQuantity(principal.uuid(), uuid, body.getQuantity());
        return new Response("Количество товара изменено", HttpStatus.OK, updated);
    }

    @DeleteCartItemOperation
    @DeleteMapping("/{uuid}")
    public Response deleteCartItem(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @PathVariable String uuid) {
        cartService.deleteCartItem(principal.uuid(), uuid);
        return new Response("Позиция корзины удалена", HttpStatus.OK, null);
    }
}
