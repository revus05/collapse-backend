package com.example.collapse.dto.user;

import com.example.collapse.enums.Currency;
import com.example.collapse.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCreateUserRequestDTO {
    @Schema(description = "User image URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String image;

    @Schema(description = "FirstName", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @Schema(description = "LastName", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    @Schema(description = "MiddleName", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Отчество обязательно")
    private String middleName;

    @Schema(description = "Email address", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "Email должен быть валидным")
    @NotBlank(message = "Email обязателен")
    private String email;

    @Schema(description = "Phone", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Номер телефона обязателен")
    private String phone;

    @Schema(description = "User password", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 128, message = "Пароль должен содержать от 8 до 128 символов")
    private String password;

    @Schema(description = "Preferred currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private Currency currency;

    @Schema(description = "User role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private Role role;
}
