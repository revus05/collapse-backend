package com.example.collapse.swagger.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Удаление заказа",
        description = "Удаление заказа (только для администратора)",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Заказ удалён"
                )
        }
)
public @interface DeleteOrderOperation {}
