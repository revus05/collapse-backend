package com.example.collapse.dto.user;

import com.example.collapse.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCurrencyRequestDTO {
    @Schema(description = "Новая валюта пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private Currency currency;
}
