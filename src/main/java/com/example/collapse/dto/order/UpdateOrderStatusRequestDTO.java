package com.example.collapse.dto.order;

import com.example.collapse.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateOrderStatusRequestDTO {
    @Schema(description = "Order status", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private OrderStatus status;
}
