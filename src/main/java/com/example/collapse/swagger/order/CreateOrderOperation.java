package com.example.collapse.swagger.order;

import com.example.collapse.dto.order.CreateOrderRequestDTO;
import com.example.collapse.dto.order.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Создание заказа",
        description = "Создает новый заказ",
        requestBody = @RequestBody(
                description = "Данные для создания заказа",
                content = @Content(
                        schema = @Schema(implementation = CreateOrderRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Пример запроса",
                                value = """
                    {
                      "products": [{...},{...}],
                    }"""
                        )
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Заказ успешно создан",
                        content = @Content(
                                schema = @Schema(implementation = OrderDTO.class)
                        )
                )
        }
)
public @interface CreateOrderOperation {}