package com.example.collapse.entity;

import com.example.collapse.dto.user.SignUpUserRequestDTO;
import com.example.collapse.enums.Currency;
import com.example.collapse.enums.Role;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Hidden
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String image;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Currency currency = Currency.BYN;

    @Column(nullable = false)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_cart_products",
        joinColumns = @JoinColumn(name = "user_uuid"),
        inverseJoinColumns = @JoinColumn(name = "product_uuid")
    )
    private List<Product> cart = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public User() {}

    public User(SignUpUserRequestDTO requestBody) {
        this.firstName = requestBody.getFirstName();
        this.lastName = requestBody.getLastName();
        this.middleName = requestBody.getMiddleName();
        this.email = requestBody.getEmail();
        this.phone = requestBody.getPhone();
        this.password = requestBody.getPassword();
    }
}