package com.example.collapse.dto.product;

import com.example.collapse.enums.Colors;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class ProductRequestDTO {
    @Schema(description = "Список изображений")
    private ArrayList<String> images = new ArrayList<>();

    @Schema(description = "Название продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String title;

    @Schema(description = "Цвет внутри", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private ArrayList<Colors> insideColors;

    @Schema(description = "Цвет снаружи", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private ArrayList<Colors> outsideColors;

    @Schema(description = "Цена BYN", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private BigDecimal priceBYN;

    @Schema(description = "Цена RUB", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private BigDecimal priceRUB;

    @Schema(description = "Скидочная цена BYN")
    private BigDecimal discountPriceBYN;

    @Schema(description = "Скидочная цена RUB")
    private BigDecimal discountPriceRUB;

    @Schema(description = "Описание продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String description;
}