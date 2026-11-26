package ru.tsyplakov.airlines.mapper;

import org.springframework.stereotype.Component;
import ru.tsyplakov.airlines.domain.User;
import ru.tsyplakov.airlines.domain.UserRole;
import ru.tsyplakov.airlines.dto.user.UserCreateRequest;
import ru.tsyplakov.airlines.dto.user.UserResponse;
import ru.tsyplakov.airlines.dto.user.UserUpdateRequest;

@Component
public class UserMapper implements ToResponseMapper<User, UserResponse> {
    public User toEntity(Long id, UserCreateRequest request, String passwordHash) {
        return new User(id, request.fullName(), request.email(), passwordHash, UserRole.PASSENGER);
    }

    @Override
    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }

    public User updateEntity(User user, UserUpdateRequest request, String passwordHash) {
        if (request.fullName() != null) {
            user.setFullName(request.fullName());
        }
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (passwordHash != null) {
            user.setPasswordHash(passwordHash);
        }
        return user;
    }
}
