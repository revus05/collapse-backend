package com.example.collapse.swagger.user;

import com.example.collapse.dto.user.AdminUpdateUserRequestDTO;
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
        summary = "Обновление пользователя (ADMIN)",
        description = "Обновляет пользователя по uuid из админ-панели. Доступно только ADMIN",
        requestBody =
                @RequestBody(
                        description = "Данные для обновления пользователя",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                AdminUpdateUserRequestDTO.class))),
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно обновлен",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
        })
public @interface UpdateUserByAdminOperation {}
