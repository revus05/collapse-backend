package com.example.collapse.dto.cart;

import com.example.collapse.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToCartRequestDTO {
    @Schema(description = "Product uuid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productUuid;

    @Schema(description = "Quantity", requiredMode = Schema.RequiredMode.REQUIRED)
    private int quantity;

    @Schema(description = "Inside color", requiredMode = Schema.RequiredMode.REQUIRED)
    private Color insideColor;

    @Schema(description = "Outside color", requiredMode = Schema.RequiredMode.REQUIRED)
    private Color outsideColor;
}