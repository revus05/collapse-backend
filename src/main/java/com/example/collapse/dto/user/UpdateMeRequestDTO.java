package com.example.collapse.dto.user;

import com.example.collapse.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMeRequestDTO {
    @Schema(description = "User image URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String image;

    @Schema(description = "FirstName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String firstName;

    @Schema(description = "LastName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String lastName;

    @Schema(description = "MiddleName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String middleName;

    @Schema(description = "Email address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Email(message = "Email должен быть валидным")
    @Nullable
    private String email;

    @Schema(description = "Phone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String phone;

    @Schema(description = "User password", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(min = 8, max = 128, message = "Пароль должен содержать от 8 до 128 символов")
    @Nullable
    private String password;

    @Schema(description = "Preferred currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private Currency currency;
}
