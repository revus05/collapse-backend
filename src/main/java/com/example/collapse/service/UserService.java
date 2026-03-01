package com.example.collapse.service;

import com.example.collapse.dto.user.SignInUserRequestDTO;
import com.example.collapse.dto.user.SignUpUserRequestDTO;
import com.example.collapse.dto.user.UpdateCurrencyRequestDTO;
import com.example.collapse.dto.user.UserDTO;
import com.example.collapse.entity.User;
import com.example.collapse.exception.UnauthorizedException;
import com.example.collapse.exception.UserAlreadyExistsException;
import com.example.collapse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO signUpUser(SignUpUserRequestDTO signUpUserRequestDTO) throws UserAlreadyExistsException {
        checkUserExists(signUpUserRequestDTO.getEmail(), signUpUserRequestDTO.getPhone());

        String encodedPassword = passwordEncoder.encode(signUpUserRequestDTO.getPassword());
        signUpUserRequestDTO.setPassword(encodedPassword);

        return new UserDTO(userRepository.save(new User(signUpUserRequestDTO)));
    }

    public UserDTO signInUser(SignInUserRequestDTO signInUserRequestDTO) throws UnauthorizedException {
        User foundUser = userRepository.findByEmail(signInUserRequestDTO.getEmail())
            .filter(user -> passwordEncoder.matches(signInUserRequestDTO.getPassword(), user.getPassword()))
            .orElseThrow(() -> new UnauthorizedException("Нет пользователя с введенными данными"));

        return new UserDTO(foundUser);
    }

    public UserDTO getMe(String userUuid) throws UnauthorizedException {
        User foundUser = userRepository.findById(userUuid)
            .orElseThrow(() -> new UnauthorizedException("Нет пользователя для такого uuid"));

        return new UserDTO(foundUser);
    }

    public UserDTO updateCurrency(String userUuid, UpdateCurrencyRequestDTO dto) throws UnauthorizedException {
        User foundUser = userRepository.findById(userUuid)
            .orElseThrow(() -> new UnauthorizedException("Нет пользователя для такого uuid"));

        foundUser.setCurrency(dto.getCurrency());

        System.out.println(dto.getCurrency());

        userRepository.save(foundUser);

        return new UserDTO(foundUser);
    }

    private void checkUserExists(String email, String phone) {
        userRepository.findByPhone(phone)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("phone", "Пользователь с таким телефоном уже существует");
                });

        userRepository.findByEmail(email)
            .ifPresent(user -> {
                throw new UserAlreadyExistsException("email", "Пользователь с таким email уже существует");
            });
    }
}
