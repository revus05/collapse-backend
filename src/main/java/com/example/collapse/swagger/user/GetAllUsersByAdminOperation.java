package com.example.collapse.swagger.user;

import com.example.collapse.dto.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
        summary = "Получение списка пользователей (ADMIN)",
        description = "Возвращает список всех пользователей. Доступно только ADMIN",
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список пользователей",
                    content =
                            @Content(
                                    array =
                                            @ArraySchema(
                                                    schema =
                                                            @Schema(
                                                                    implementation =
                                                                            UserDTO.class))))
        })
public @interface GetAllUsersByAdminOperation {}
