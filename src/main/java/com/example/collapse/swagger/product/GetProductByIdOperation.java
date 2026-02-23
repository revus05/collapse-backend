package com.example.collapse.swagger.product;

import com.example.collapse.dto.product.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Получение продукта по UUID",
        description = "Возвращает продукт по его уникальному идентификатору",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Продукт найден",
                        content = @Content(
                                schema = @Schema(implementation = ProductDTO.class)
                        )
                )
        }
)
public @interface GetProductByIdOperation {}