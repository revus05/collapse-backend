package com.example.collapse.dto.cloudinary;

import io.swagger.v3.oas.annotations.media.Schema;

public record UploadFileResponseDTO(
    @Schema(description = "Uploaded file path", requiredMode = Schema.RequiredMode.REQUIRED)
        String filepath) {}
