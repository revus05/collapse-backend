package com.example.collapse.swagger.order;

import com.example.collapse.dto.order.OrderDTO;
import com.example.collapse.dto.order.UpdateOrderStatusRequestDTO;
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
        summary = "Изменение статуса заказа",
        description = "Изменение статуса заказа (только для администратора)",
        requestBody = @RequestBody(
                description = "Новый статус заказа",
                content = @Content(
                        schema = @Schema(implementation = UpdateOrderStatusRequestDTO.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Статус заказа изменён",
                        content = @Content(
                                schema = @Schema(implementation = OrderDTO.class)
                        )
                )
        }
)
public @interface UpdateOrderStatusOperation {}
