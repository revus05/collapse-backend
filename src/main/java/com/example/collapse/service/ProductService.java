package com.example.collapse.service;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.dto.product.ProductRequestDTO;
import com.example.collapse.entity.Product;
import com.example.collapse.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public ProductDTO createProduct(ProductRequestDTO dto) {
        Product product = new Product(dto);
        return new ProductDTO(productRepo.save(product));
    }

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductDTO getProductById(String uuid) {
        return productRepo.findById(uuid).map(ProductDTO::new).orElseThrow();
    }

    public ProductDTO updateProduct(String uuid, ProductRequestDTO dto) {
        Product product = productRepo.findById(uuid).orElseThrow();
        product.setTitle(dto.getTitle());
        product.setImages(dto.getImages());
        product.setInsideColors(dto.getInsideColors());
        product.setOutsideColors(dto.getOutsideColors());
        product.setPriceBYN(dto.getPriceBYN());
        product.setPriceRUB(dto.getPriceRUB());
        product.setDiscountPriceBYN(dto.getDiscountPriceBYN());
        product.setDiscountPriceRUB(dto.getDiscountPriceRUB());
        product.setDescription(dto.getDescription());
        return new ProductDTO(productRepo.save(product));
    }

    public void deleteProduct(String uuid) {
        productRepo.deleteById(uuid);
    }
}