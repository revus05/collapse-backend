package com.example.collapse.repository;

import com.example.collapse.entity.CartItem;
import com.example.collapse.enums.Color;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartItem, String> {
    List<CartItem> findByUser_UuidOrderByCreatedAtDesc(String userUuid);
    List<CartItem> findAllByUuidIn(List<String> uuids);
    Optional<CartItem> findByUser_UuidAndProduct_UuidAndInsideColorAndOutsideColorAndOrderIsNull(
            String userUuid, String productUuid, Color insideColor, Color outsideColor);
}
