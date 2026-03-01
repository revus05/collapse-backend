package com.example.collapse.service;

import com.example.collapse.dto.cart.AddToCartRequestDTO;
import com.example.collapse.dto.cart.CartItemDTO;
import com.example.collapse.entity.CartItem;
import com.example.collapse.entity.Product;
import com.example.collapse.entity.User;
import com.example.collapse.repository.CartRepository;
import com.example.collapse.repository.ProductRepository;
import com.example.collapse.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartItemDTO addToCart(String userUuid, AddToCartRequestDTO addToCartRequestDTO) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Product product = productRepository.findById(addToCartRequestDTO.getProductUuid())
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        CartItem cartItem = new CartItem(user, product, addToCartRequestDTO);

        user.getCartItems().add(cartItem);

        userRepository.save(user);

        return new CartItemDTO(cartItem);
    }

    @Transactional()
    public List<CartItemDTO> getCart(String userUuid) {
        userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return cartRepository.findByUser_UuidOrderByCreatedAtDesc(userUuid).stream().map(CartItemDTO::new).toList();
    }
}
