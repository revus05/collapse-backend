package com.example.collapse.swagger.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Удаление продукта",
        description = "Удаляет продукт по UUID",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Продукт удален"
                )
        }
)
public @interface DeleteProductOperation {}