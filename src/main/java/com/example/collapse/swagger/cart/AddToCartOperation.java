package com.example.collapse.swagger.cart;

import com.example.collapse.dto.cart.AddToCartRequestDTO;
import com.example.collapse.dto.cart.CartItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Добавление в корзину",
        description = "Добавление продукта в корзину",
        requestBody = @RequestBody(
                description = "Данные товара для добавления в корзину",
                content = @Content(
                        schema = @Schema(implementation = AddToCartRequestDTO.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Товар успешно добавлен в корзину",
                        content = @Content(
                                schema = @Schema(implementation = CartItemDTO.class)
                        )
                )
        }
)
public @interface AddToCartOperation {}