package ru.tsyplakov.airlines.service;

import ru.tsyplakov.airlines.domain.User;
import ru.tsyplakov.airlines.repository.UserRepository;

public class UserService extends BaseService<User>{

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public User create(
            String fullName,
            String email,
            String passwordHash
    ) {
        Long id = repository.getNextId();
        User user = new User(id, fullName, email, passwordHash);
        repository.create(user);
        return user;
    }
}
