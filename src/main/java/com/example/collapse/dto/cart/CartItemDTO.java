package com.example.collapse.dto.cart;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.entity.CartItem;
import com.example.collapse.enums.Color;
import com.example.collapse.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class CartItemDTO {
    @Schema(description = "User uuid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uuid;

    @Schema(description = "Product", requiredMode = Schema.RequiredMode.REQUIRED)
    private ProductDTO product;

    @Schema(description = "Price", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    @Schema(description = "Currency", requiredMode = Schema.RequiredMode.REQUIRED)
    private Currency currency;

    @Schema(description = "Quantity", requiredMode = Schema.RequiredMode.REQUIRED)
    private int quantity;

    @Schema(description = "Inside color", requiredMode = Schema.RequiredMode.REQUIRED)
    private Color insideColor;

    @Schema(description = "Outside color", requiredMode = Schema.RequiredMode.REQUIRED)
    private Color outsideColor;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public CartItemDTO(CartItem cartItem) {
        this.uuid = cartItem.getUuid();
        this.product = new ProductDTO(cartItem.getProduct());
        this.quantity = cartItem.getQuantity();
        this.insideColor = cartItem.getInsideColor();
        this.outsideColor = cartItem.getOutsideColor();
        this.createdAt = cartItem.getCreatedAt();
        this.updatedAt = cartItem.getUpdatedAt();
    }
}