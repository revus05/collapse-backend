package com.example.collapse.swagger.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Удаление позиции",
        description = "Удаление позиции из корзины",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Позиция корзины удалена"
                )
        }
)
public @interface DeleteCartItemOperation {}
