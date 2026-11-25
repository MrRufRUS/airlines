package ru.tsyplakov.airlines.dto.user;

import jakarta.validation.constraints.*;
import ru.tsyplakov.airlines.domain.UserRole;

public record UserUpdateRequest(

        @Size(max = 255)
        @Pattern(regexp = "(?s).*\\S.*")
        String fullName,

        @Email
        @Size(max = 255)
        @Pattern(regexp = "(?s).*\\S.*")
        String email,

        @Size(min = 8, max = 128)
        @Pattern(regexp = "(?s).*\\S.*")
        String password,

        UserRole role

) {}