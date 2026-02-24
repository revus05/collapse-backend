package com.example.collapse.dto.product;

import com.example.collapse.entity.Product;
import com.example.collapse.enums.Colors;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

@Getter
@Setter
public class ProductDTO {

    @Schema(description = "Product uuid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uuid;

    @Schema(description = "List of image URLs", requiredMode = Schema.RequiredMode.REQUIRED)
    private ArrayList<String> images;

    @Schema(description = "Product title", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Inside color", requiredMode = Schema.RequiredMode.REQUIRED)
    private ArrayList<Colors> insideColors;

    @Schema(description = "Outside color", requiredMode = Schema.RequiredMode.REQUIRED)
    private ArrayList<Colors> outsideColors;

    @Schema(description = "Price in BYN", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal priceBYN;

    @Schema(description = "Price in RUB", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal priceRUB;

    @Schema(description = "Discount price in BYN", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private BigDecimal discountPriceBYN;

    @Schema(description = "Discount price in RUB", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Nullable
    private BigDecimal discountPriceRUB;

    @Schema(description = "Product description", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "Creation timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant createdAt;

    @Schema(description = "Last update timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant updatedAt;

    public ProductDTO(Product product) {
        this.uuid = product.getUuid();
        this.images = product.getImages();
        this.title = product.getTitle();
        this.insideColors = product.getInsideColors();
        this.outsideColors = product.getOutsideColors();
        this.priceBYN = product.getPriceBYN();
        this.priceRUB = product.getPriceRUB();
        this.discountPriceBYN = product.getDiscountPriceBYN();
        this.discountPriceRUB = product.getDiscountPriceRUB();
        this.description = product.getDescription();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
}