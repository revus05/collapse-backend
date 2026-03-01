package com.example.collapse.swagger.cart;

import com.example.collapse.dto.cart.CartItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Получение корзины товаров пользователя",
        description = "Возвращает список всех продуктов",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Корзина товаров успешно получена",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = CartItemDTO.class))
                        )
                )
        }
)
public @interface GetCartOperation {
}
