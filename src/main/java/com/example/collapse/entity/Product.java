package com.example.collapse.entity;

import com.example.collapse.dto.product.ProductRequestDTO;
import com.example.collapse.enums.Colors;
import com.example.collapse.enums.Tags;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

@Entity
@Hidden
@Getter
@Setter
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private ArrayList<String> images;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private ArrayList<Colors> insideColors;

    @Column(nullable = false)
    private ArrayList<Colors> outsideColors;

    @Column(nullable = false)
    private ArrayList<Tags> tags;

    @Column(nullable = false)
    private BigDecimal priceBYN;

    @Column(nullable = false)
    private BigDecimal priceRUB;

    private BigDecimal discountPriceBYN;
    private BigDecimal discountPriceRUB;

    @Lob
    @Column(nullable = false)
    private String description;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public Product() {}

    public Product(ProductRequestDTO dto) {
        this.images = dto.getImages();
        this.title = dto.getTitle();
        this.insideColors = dto.getInsideColors();
        this.outsideColors = dto.getOutsideColors();
        this.tags = dto.getTags();
        this.priceBYN = dto.getPriceBYN();
        this.priceRUB = dto.getPriceRUB();
        this.discountPriceBYN = dto.getDiscountPriceBYN();
        this.discountPriceRUB = dto.getDiscountPriceRUB();
        this.description = dto.getDescription();
    }
}