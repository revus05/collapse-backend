package com.example.collapse.entity;

import com.example.collapse.dto.cart.AddToCartRequestDTO;
import com.example.collapse.enums.Color;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Hidden
@Getter
@Setter
@Table(name = "cart_items")
@EntityListeners(AuditingEntityListener.class)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_uuid")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

    private int quantity;

    private Color insideColor;

    private Color outsideColor;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public CartItem() {}

    public CartItem(User user, Product product, AddToCartRequestDTO addToCartRequestDTO) {
        this.user = user;
        this.product = product;
        this.quantity = addToCartRequestDTO.getQuantity();
        this.insideColor = addToCartRequestDTO.getInsideColor();
        this.outsideColor = addToCartRequestDTO.getOutsideColor();
    }
}