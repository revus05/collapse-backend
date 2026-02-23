package com.example.collapse.swagger.product;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.dto.product.ProductRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Обновление продукта",
        description = "Обновляет продукт по UUID",
        requestBody = @RequestBody(
                description = "Данные для обновления продукта",
                content = @Content(
                        schema = @Schema(implementation = ProductRequestDTO.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Продукт обновлен",
                        content = @Content(
                                schema = @Schema(implementation = ProductDTO.class)
                        )
                )
        }
)
public @interface UpdateProductOperation {}