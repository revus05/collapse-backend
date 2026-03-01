package com.example.collapse.dto.order;

import com.example.collapse.dto.cart.CartItemDTO;
import com.example.collapse.entity.Order;
import com.example.collapse.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    @Schema(description = "Order uuid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uuid;

    @Schema(description = "Cart items list", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<CartItemDTO> orderItems;

    @Schema(description = "Total order price", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal totalAmount;

    @Schema(description = "Order currency", requiredMode = Schema.RequiredMode.REQUIRED)
    private Currency currency;

    @Schema(description = "Creation timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant createdAt;

    @Schema(description = "Last update timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant updatedAt;

    public OrderDTO(Order order) {
        this.uuid = order.getUuid();
        this.orderItems = order.getOrderItems().stream().map(CartItemDTO::new).toList();
        this.totalAmount = order.getTotalAmount();
        this.currency = order.getCurrency();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }
}
