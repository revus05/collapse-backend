package com.example.collapse.swagger.user;

import com.example.collapse.dto.user.UpdateCurrencyRequestDTO;
import com.example.collapse.dto.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(
    summary = "Обновление валюты пользователя",
    description = "Обновляет валюту магазина пользователя",
    requestBody = @RequestBody(
        description = "Новая валюта пользователя",
        content = @Content(
            schema = @Schema(implementation = UpdateCurrencyRequestDTO.class)
        )
    ),
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Валюта пользователя успешно обновлена",
            content = @Content(schema = @Schema(implementation = UserDTO.class))
        ),
    }
)
public @interface UpdateCurrencyOperation {
}
