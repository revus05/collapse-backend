package com.example.collapse.service;

import com.example.collapse.dto.product.ProductDTO;
import com.example.collapse.dto.user.SignInUserRequestDTO;
import com.example.collapse.dto.user.SignUpUserRequestDTO;
import com.example.collapse.dto.user.UpdateCurrencyRequestDTO;
import com.example.collapse.dto.user.UserDTO;
import com.example.collapse.entity.Product;
import com.example.collapse.entity.User;
import com.example.collapse.exception.UnauthorizedException;
import com.example.collapse.exception.UserAlreadyExistsException;
import com.example.collapse.repository.ProductRepo;
import com.example.collapse.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;
    private final ProductRepo productRepository;
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

    public ProductDTO toggleInCart(String userUuid, String productUuid) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Product product = productRepository.findById(productUuid)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        if (user.getCart().contains(product)) {
            user.getCart().remove(product);
        } else {
            user.getCart().add(product);
        }
        userRepository.save(user);

        return new ProductDTO(product);
    }

    @Transactional()
    public List<ProductDTO> getCart(String userUuid) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return user.getCart().stream().map(ProductDTO::new).toList();
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
