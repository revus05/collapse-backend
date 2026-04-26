package com.example.collapse.swagger.cart;

import com.example.collapse.dto.cart.CartItemDTO;
import com.example.collapse.dto.cart.UpdateCartItemRequestDTO;
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
        summary = "Изменение количества",
        description = "Изменение количества товара в позиции корзины",
        requestBody = @RequestBody(
                description = "Новое количество",
                content = @Content(
                        schema = @Schema(implementation = UpdateCartItemRequestDTO.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Количество товара изменено",
                        content = @Content(
                                schema = @Schema(implementation = CartItemDTO.class)
                        )
                )
        }
)
public @interface UpdateCartItemOperation {}
