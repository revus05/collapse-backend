package com.example.collapse.controller;

import com.example.collapse.config.JwtUserPrincipal;
import com.example.collapse.dto.response.Response;
import com.example.collapse.dto.user.AdminCreateUserRequestDTO;
import com.example.collapse.dto.user.AdminUpdateUserRequestDTO;
import com.example.collapse.dto.user.SignInUserRequestDTO;
import com.example.collapse.dto.user.SignUpUserRequestDTO;
import com.example.collapse.dto.user.UpdateMeRequestDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String token = jwtService.generateToken(loggedUser);
        response.addCookie(jwtService.createJwtCookie(request, token, 60 * 60 * 24 * 7));
        return new Response("Пользователь успешно авторизован", HttpStatus.OK, loggedUser);
    }

    @GetMeOperation
    @GetMapping("/me")
    public Response getMeWithJwt(@AuthenticationPrincipal JwtUserPrincipal principal) {
        UserDTO loggedUser = userService.getMe(principal.uuid());
        return new Response("Пользователь успешно авторизован", HttpStatus.OK, loggedUser);
    }

    @UpdateMeOperation
    @PutMapping("/me")
    public Response updateMe(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @Valid @RequestBody UpdateMeRequestDTO updateMeRequestDTO) {
        UserDTO updatedUser =
                userService.updateMe(principal.uuid(), updateMeRequestDTO);
        return new Response("Данные пользователя успешно обновлены", HttpStatus.OK, updatedUser);
    }


    @SignOutOperation
    @PostMapping("/sign-out")
    public Response singOut(HttpServletResponse response, HttpServletRequest request) {
        response.addCookie(jwtService.createJwtCookie(request, "", 0));
        return new Response("Пользователь успешно вышел", HttpStatus.OK);
    }


    @UpdateCurrencyOperation
    @PostMapping("/update-currency")
    public Response updateCurrency(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @Valid @RequestBody UpdateCurrencyRequestDTO updateCurrencyRequestDTO) {
        UserDTO updatedUser = userService.updateCurrency(principal.uuid(), updateCurrencyRequestDTO);
        return new Response("Валюта пользователя успешно обновлена", HttpStatus.OK, updatedUser);
    }

    @GetMapping("/admin")
    @GetAllUsersByAdminOperation
    public Response getAllUsersByAdmin() {
        List<UserDTO> users = userService.getAllUsers();
        return new Response("Список пользователей", HttpStatus.OK, users);
    }

    @PostMapping("/admin")
    @CreateUserByAdminOperation
    public Response createUserByAdmin(@Valid @RequestBody AdminCreateUserRequestDTO dto) {
        UserDTO createdUser = userService.createUserByAdmin(dto);
        return new Response("Пользователь успешно создан", HttpStatus.CREATED, createdUser);
    }

    @PutMapping("/admin/{uuid}")
    @UpdateUserByAdminOperation
    public Response updateUserByAdmin(@PathVariable String uuid, @Valid @RequestBody AdminUpdateUserRequestDTO dto) {
        UserDTO updatedUser = userService.updateUserByAdmin(uuid, dto);
        return new Response("Пользователь успешно обновлен", HttpStatus.OK, updatedUser);
    }

    @DeleteMapping("/admin/{uuid}")
    @DeleteUserByAdminOperation
    public Response deleteUserByAdmin(@PathVariable String uuid) {
        userService.deleteUserByAdmin(uuid);
        return new Response("Пользователь успешно удален", HttpStatus.OK);
    }
}
