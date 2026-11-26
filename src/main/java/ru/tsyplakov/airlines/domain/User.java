package ru.tsyplakov.airlines.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity {

    private String fullName;
    private String email;
    private String passwordHash;
    private UserRole role;

    public User(Long id, String fullName, String email, String passwordHash, UserRole role) {
        super(id);
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
