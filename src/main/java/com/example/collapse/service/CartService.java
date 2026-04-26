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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Transactional
    public CartItemDTO addToCart(String userUuid, AddToCartRequestDTO addToCartRequestDTO) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Product product = productRepository.findById(addToCartRequestDTO.getProductUuid())
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        Optional<CartItem> existing = cartRepository
                .findByUser_UuidAndProduct_UuidAndInsideColorAndOutsideColorAndOrderIsNull(
                        userUuid,
                        product.getUuid(),
                        addToCartRequestDTO.getInsideColor(),
                        addToCartRequestDTO.getOutsideColor());

        if (existing.isPresent()) {
            CartItem cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity() + addToCartRequestDTO.getQuantity());
            CartItem saved = cartRepository.save(cartItem);
            return new CartItemDTO(saved);
        }

        CartItem cartItem = new CartItem(user, product, addToCartRequestDTO);

        user.getCartItems().add(cartItem);

        userRepository.save(user);

        return new CartItemDTO(cartItem);
    }

    @Transactional
    public List<CartItemDTO> getCart(String userUuid) {
        userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return cartRepository.findByUser_UuidOrderByCreatedAtDesc(userUuid).stream().map(CartItemDTO::new).toList();
    }

    @Transactional
    public CartItemDTO updateQuantity(String userUuid, String cartItemUuid, int quantity) {
        if (quantity < 1) {
            throw new RuntimeException("Количество должно быть не меньше 1");
        }

        CartItem cartItem = cartRepository.findById(cartItemUuid)
                .orElseThrow(() -> new RuntimeException("Позиция корзины не найдена"));

        if (cartItem.getUser() == null || !cartItem.getUser().getUuid().equals(userUuid)) {
            throw new AccessDeniedException("Нет доступа к этой позиции корзины");
        }

        cartItem.setQuantity(quantity);
        CartItem saved = cartRepository.save(cartItem);
        return new CartItemDTO(saved);
    }

    @Transactional
    public void deleteCartItem(String userUuid, String cartItemUuid) {
        CartItem cartItem = cartRepository.findById(cartItemUuid)
                .orElseThrow(() -> new RuntimeException("Позиция корзины не найдена"));

        if (cartItem.getUser() == null || !cartItem.getUser().getUuid().equals(userUuid)) {
            throw new AccessDeniedException("Нет доступа к этой позиции корзины");
        }

        cartRepository.delete(cartItem);
    }
}
