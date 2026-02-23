package com.example.collapse.swagger.product;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.dto.product.ProductRequestDTO;
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
        summary = "Создание продукта",
        description = "Создает новый продукт",
        requestBody = @RequestBody(
                description = "Данные для создания продукта",
                content = @Content(
                        schema = @Schema(implementation = ProductRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Пример запроса",
                                value = """
                    {
                      "images": ["https://example.com/image1.jpg"],
                      "title": "Сумка",
                      "insideColor": "BLACK",
                      "outsideColor": "WHITE",
                      "priceBYN": 120.50,
                      "priceRUB": 4000,
                      "discountPriceBYN": 100,
                      "discountPriceRUB": 3500,
                      "description": "Стильная сумка"
                    }"""
                        )
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Продукт успешно создан",
                        content = @Content(
                                schema = @Schema(implementation = ProductDTO.class)
                        )
                )
        }
)
public @interface CreateProductOperation {}