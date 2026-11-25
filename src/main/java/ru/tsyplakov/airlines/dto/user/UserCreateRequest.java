package ru.tsyplakov.airlines.dto.user;

import jakarta.validation.constraints.*;
import ru.tsyplakov.airlines.domain.UserRole;

public record UserCreateRequest(

        @NotBlank
        @Size(max = 255)
        String fullName,

        @NotBlank
        @Email
        @Size(max = 255)
        String email,

        @NotBlank
        @Size(min = 8, max = 128)
        String password

) {}