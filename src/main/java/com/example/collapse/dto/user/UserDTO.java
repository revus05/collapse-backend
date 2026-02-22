package com.example.collapse.dto.user;

import com.example.collapse.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class UserDTO {
    @Schema(description = "User uuid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uuid;

    @Schema(description = "User image URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @Nullable
    private String image;

    @Schema(description = "FirstName", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(description = "LastName", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(description = "MiddleName", requiredMode = Schema.RequiredMode.REQUIRED)
    private String middleName;

    @Schema(description = "Email address", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Phone number", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @Schema(description = "Creation timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant createdAt;

    @Schema(description = "Last update timestamp", type = "string", format = "date-time", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant updatedAt;

    public UserDTO(User user) {
        this.uuid = user.getUuid();
        this.image = user.getImage();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.middleName = user.getMiddleName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}