package ru.tsyplakov.airlines.dto.user;

import ru.tsyplakov.airlines.domain.UserRole;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        UserRole role
) {}