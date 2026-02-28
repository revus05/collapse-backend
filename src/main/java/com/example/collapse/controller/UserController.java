package com.example.collapse.controller;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.dto.response.Response;
import com.example.collapse.dto.user.SignInUserRequestDTO;
import com.example.collapse.dto.user.SignUpUserRequestDTO;
import com.example.collapse.dto.user.UpdateCurrencyRequestDTO;
import com.example.collapse.dto.user.UserDTO;
import com.example.collapse.service.JwtService;
import com.example.collapse.service.UserService;
import com.example.collapse.swagger.user.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Управление пользователями")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @SignUpOperation
    @PostMapping("/sign-up")
    public Response signUpUser(@Valid @RequestBody SignUpUserRequestDTO signUpUserRequestDTO) {
        return new Response(
                "Пользователь успешно создан",
                HttpStatus.CREATED,
                userService.signUpUser(signUpUserRequestDTO));
    }

    @SignInOperation
    @PostMapping("/sign-in")
    public Response signInUser(
            @Valid @RequestBody SignInUserRequestDTO signInUserRequestDTO, HttpServletResponse response, HttpServletRequest request) {
        UserDTO loggedUser = userService.signInUser(signInUserRequestDTO);
        String token = jwtService.generateToken(loggedUser.getUuid());
        response.addCookie(jwtService.createJwtCookie(request, token, 60 * 60 * 24 * 7));
        return new Response("Пользователь успешно авторизован", HttpStatus.OK, loggedUser);
    }

    @GetMeOperation
    @GetMapping("/me")
    public Response getMeWithJwt() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        UserDTO loggedUser = userService.getMe(((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername());
        return new Response("Пользователь успешно авторизован", HttpStatus.OK, loggedUser);
    }


    @SignOutOperation
    @PostMapping("/sign-out")
    public Response singOut(HttpServletResponse response, HttpServletRequest request) {
        response.addCookie(jwtService.createJwtCookie(request, "", 0));
        return new Response("Пользователь успешно вышел", HttpStatus.OK);
    }


    @PostMapping("/update-currency")
    public Response updateCurrency(@Valid @RequestBody UpdateCurrencyRequestDTO updateCurrencyRequestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO updatedUser = userService.updateCurrency(((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername(), updateCurrencyRequestDTO);
        return new Response("Пользователь успешно вышел", HttpStatus.OK, updatedUser);
    }

    @PostMapping("/toggle-in-cart/{productUuid}")
    public Response addToCart(@PathVariable String productUuid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProductDTO product = userService.toggleInCart(((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername(), productUuid);
        return new Response("Товар успешно добавлен в корзину", HttpStatus.OK, product);
    }

    @GetMapping("/get-cart")
    public Response getCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<ProductDTO> cart = userService.getCart(((UserDetails) Objects.requireNonNull(auth.getPrincipal())).getUsername());
        return new Response("Корзина товаров успешно получена", HttpStatus.OK, cart);
    }
}