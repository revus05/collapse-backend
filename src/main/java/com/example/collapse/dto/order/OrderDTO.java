package com.example.collapse.dto.order;

import com.example.collapse.dto.cart.CartItemDTO;
import com.example.collapse.dto.user.UserDTO;
import com.example.collapse.entity.Order;
import com.example.collapse.enums.Currency;
import com.example.collapse.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
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

    @Schema(description = "Customer", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserDTO user;

    @Schema(description = "Cart items list", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<CartItemDTO> orderItems;

    @Schema(description = "Total order price", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal totalAmount;

    @Schema(description = "Order currency", requiredMode = Schema.RequiredMode.REQUIRED)
    private Currency currency;

    @Schema(description = "Order status", requiredMode = Schema.RequiredMode.REQUIRED)
    private OrderStatus status;

    @Schema(description = "Delivery phone", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @Schema(description = "Delivery address", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @Schema(description = "Order comment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private String comment;

    @Schema(description = "Creation timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant createdAt;

    @Schema(description = "Last update timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant updatedAt;

    public OrderDTO(Order order) {
        this.uuid = order.getUuid();
        this.user = new UserDTO(order.getUser());
        this.orderItems = order.getOrderItems().stream().map(CartItemDTO::new).toList();
        this.totalAmount = order.getTotalAmount();
        this.currency = order.getCurrency();
        this.status = order.getStatus();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.comment = order.getComment();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }
}
