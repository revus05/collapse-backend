package com.example.collapse.swagger.order;

import com.example.collapse.dto.order.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Получение всех заказов",
        description = "Получение списка всех заказов (только для администратора)",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Список заказов получен",
                        content = @Content(
                                array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class))
                        )
                )
        }
)
public @interface GetAllOrdersOperation {}
