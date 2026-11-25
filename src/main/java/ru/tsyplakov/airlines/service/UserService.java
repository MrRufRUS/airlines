package ru.tsyplakov.airlines.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tsyplakov.airlines.domain.User;
import ru.tsyplakov.airlines.dto.user.UserCreateRequest;
import ru.tsyplakov.airlines.dto.user.UserResponse;
import ru.tsyplakov.airlines.dto.user.UserUpdateRequest;
import ru.tsyplakov.airlines.exeption.NotFoundException;
import ru.tsyplakov.airlines.mapper.UserMapper;
import ru.tsyplakov.airlines.repository.UserRepository;

public class UserService extends BaseService<User, UserResponse, UserMapper> {

    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        super(userRepository, userMapper);
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(
            UserCreateRequest request
    ) {
        Long id = repository.getNextId();
        String passwordHash = passwordEncoder.encode(request.password());
        User user = mapper.toEntity(id, request, passwordHash);
        repository.create(user);
        return mapper.toResponse(user);
    }

    public UserResponse update(Long id, UserUpdateRequest request){
        User userForUpdate = repository.getById(id);
        if (userForUpdate == null) {
            throw new NotFoundException("Entity with id:" + id + " not found");
        }
        String passwordHash = passwordEncoder.encode(request.password());
        User user = mapper.updateEntity(
                repository.getById(id),
                request,
                passwordHash
        );
        repository.update(user);
        return mapper.toResponse(user);
    }
}
