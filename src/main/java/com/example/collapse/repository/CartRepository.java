package com.example.collapse.repository;

import com.example.collapse.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<CartItem, String> {
    List<CartItem> findByUser_UuidOrderByCreatedAtDesc(String userUuid);
    List<CartItem> findAllByUuidIn(List<String> uuids);
}
