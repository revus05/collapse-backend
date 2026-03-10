package com.example.collapse.swagger.user;

import com.example.collapse.dto.user.AdminCreateUserRequestDTO;
import com.example.collapse.dto.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
        summary = "Создание пользователя (ADMIN)",
        description = "Создает нового пользователя из админ-панели. Доступно только ADMIN",
        requestBody =
                @RequestBody(
                        description = "Данные для создания пользователя",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                AdminCreateUserRequestDTO.class))),
        responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Пользователь успешно создан",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
        })
public @interface CreateUserByAdminOperation {}
