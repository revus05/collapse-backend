package com.example.collapse.entity;

import com.example.collapse.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private Currency currency;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public Order() {}

    public Order(User user, List<CartItem> cartItemsToMove) {
        this.user = user;
        this.orderItems = new ArrayList<>(cartItemsToMove);

        if (!orderItems.isEmpty()) {
            this.currency = user.getCurrency();
            this.totalAmount = orderItems.stream()
                    .map(item -> {
                        BigDecimal price;

                        if (this.currency == Currency.BYN) {
                            price = item.getProduct().getPriceBYN();
                        } else {
                            price = item.getProduct().getPriceRUB();
                        }

                        return  price.multiply(BigDecimal.valueOf(item.getQuantity()));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.currency = Currency.BYN;
            this.totalAmount = BigDecimal.ZERO;
        }

        for (CartItem item : orderItems) {
            item.setOrder(this);
            item.setUser(null);
        }
    }
}
