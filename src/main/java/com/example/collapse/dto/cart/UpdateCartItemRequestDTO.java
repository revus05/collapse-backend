package com.example.collapse.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCartItemRequestDTO {
    @Schema(description = "Quantity", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(1)
    private int quantity;
}
