package com.example.collapse.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateOrderRequestDTO {
    @Schema(description = "Cart items list", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private List<String> orderItemsUuids;

    @Schema(description = "Delivery phone", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String phone;

    @Schema(description = "Delivery address", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String address;

    @Schema(description = "Order comment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String comment;
}
