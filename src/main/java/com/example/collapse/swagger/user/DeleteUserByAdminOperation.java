package com.example.collapse.swagger.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
        summary = "Удаление пользователя (ADMIN)",
        description = "Удаляет пользователя по uuid из админ-панели. Доступно только ADMIN",
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно удален")
        })
public @interface DeleteUserByAdminOperation {}
