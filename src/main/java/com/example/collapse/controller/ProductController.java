package com.example.collapse.controller;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.dto.product.ProductRequestDTO;
import com.example.collapse.dto.response.Response;
import com.example.collapse.service.ProductService;
import com.example.collapse.swagger.product.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Продукты", description = "Управление продуктами")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @CreateProductOperation
    @PostMapping
    public Response createProduct(@Valid @RequestBody ProductRequestDTO dto) {
        ProductDTO product = productService.createProduct(dto);
        return new Response("Продукт создан", HttpStatus.CREATED, product);
    }

    @GetAllProductsOperation
    @GetMapping
    public Response getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new Response("Список продуктов", HttpStatus.OK, products);
    }

    @GetProductByIdOperation
    @GetMapping("/{uuid}")
    public Response getProductById(@PathVariable String uuid) {
        ProductDTO product = productService.getProductById(uuid);
        return new Response("Продукт найден", HttpStatus.OK, product);
    }

    @UpdateProductOperation
    @PutMapping("/{uuid}")
    public Response updateProduct(@PathVariable String uuid, @Valid @RequestBody ProductRequestDTO dto) {
        ProductDTO product = productService.updateProduct(uuid, dto);
        return new Response("Продукт обновлен", HttpStatus.OK, product);
    }

    @DeleteProductOperation
    @DeleteMapping("/{uuid}")
    public Response deleteProduct(@PathVariable String uuid) {
        productService.deleteProduct(uuid);
        return new Response("Продукт удален", HttpStatus.OK, null);
    }
}