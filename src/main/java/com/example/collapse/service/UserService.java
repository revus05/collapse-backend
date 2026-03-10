package com.example.collapse.service;

import com.example.collapse.dto.user.AdminCreateUserRequestDTO;
import com.example.collapse.dto.user.AdminUpdateUserRequestDTO;
import com.example.collapse.dto.user.SignInUserRequestDTO;
import com.example.collapse.dto.user.SignUpUserRequestDTO;
import com.example.collapse.dto.user.UpdateMeRequestDTO;
import com.example.collapse.dto.user.UpdateCurrencyRequestDTO;
import com.example.collapse.dto.user.UserDTO;
import com.example.collapse.entity.User;
import com.example.collapse.enums.Currency;
import com.example.collapse.enums.Role;
import com.example.collapse.exception.UnauthorizedException;
import com.example.collapse.exception.UserAlreadyExistsException;
import com.example.collapse.repository.UserRepository;
import java.util.List;
import java.util.stream.StreamSupport;
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

    public UserDTO updateMe(String userUuid, UpdateMeRequestDTO dto) {
        User foundUser = userRepository.findById(userUuid)
                .orElseThrow(() -> new UnauthorizedException("Нет пользователя для такого uuid"));

        checkUserExistsForUpdate(dto.getEmail(), dto.getPhone(), userUuid);

        if (dto.getImage() != null) {
            foundUser.setImage(dto.getImage());
        }
        if (dto.getFirstName() != null) {
            foundUser.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            foundUser.setLastName(dto.getLastName());
        }
        if (dto.getMiddleName() != null) {
            foundUser.setMiddleName(dto.getMiddleName());
        }
        if (dto.getEmail() != null) {
            foundUser.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            foundUser.setPhone(dto.getPhone());
        }
        if (dto.getPassword() != null) {
            foundUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getCurrency() != null) {
            foundUser.setCurrency(dto.getCurrency());
        }

        return new UserDTO(userRepository.save(foundUser));
    }

    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserDTO::new)
                .toList();
    }

    public UserDTO createUserByAdmin(AdminCreateUserRequestDTO dto) {
        checkUserExists(dto.getEmail(), dto.getPhone());

        User user = new User();
        user.setImage(dto.getImage());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : Currency.BYN);
        user.setRole(dto.getRole() != null ? dto.getRole() : Role.USER);

        return new UserDTO(userRepository.save(user));
    }

    public UserDTO updateUserByAdmin(String userUuid, AdminUpdateUserRequestDTO dto) {
        User foundUser = userRepository.findById(userUuid)
                .orElseThrow(() -> new UnauthorizedException("Нет пользователя для такого uuid"));

        checkUserExistsForUpdate(dto.getEmail(), dto.getPhone(), userUuid);

        if (dto.getImage() != null) {
            foundUser.setImage(dto.getImage());
        }
        if (dto.getFirstName() != null) {
            foundUser.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            foundUser.setLastName(dto.getLastName());
        }
        if (dto.getMiddleName() != null) {
            foundUser.setMiddleName(dto.getMiddleName());
        }
        if (dto.getEmail() != null) {
            foundUser.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            foundUser.setPhone(dto.getPhone());
        }
        if (dto.getPassword() != null) {
            foundUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getCurrency() != null) {
            foundUser.setCurrency(dto.getCurrency());
        }
        if (dto.getRole() != null) {
            foundUser.setRole(dto.getRole());
        }

        return new UserDTO(userRepository.save(foundUser));
    }

    public void deleteUserByAdmin(String userUuid) {
        userRepository.deleteById(userUuid);
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

    private void checkUserExistsForUpdate(String email, String phone, String userUuid) {
        if (phone != null) {
            userRepository.findByPhone(phone).ifPresent(user -> {
                if (!user.getUuid().equals(userUuid)) {
                    throw new UserAlreadyExistsException("phone", "Пользователь с таким телефоном уже существует");
                }
            });
        }

        if (email != null) {
            userRepository.findByEmail(email).ifPresent(user -> {
                if (!user.getUuid().equals(userUuid)) {
                    throw new UserAlreadyExistsException("email", "Пользователь с таким email уже существует");
                }
            });
        }
    }
}
