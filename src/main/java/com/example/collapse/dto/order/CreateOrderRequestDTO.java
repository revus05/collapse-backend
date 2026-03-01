package com.example.collapse.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateOrderRequestDTO {
    @Schema(description = "Cart items list", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> orderItemsUuids;
}