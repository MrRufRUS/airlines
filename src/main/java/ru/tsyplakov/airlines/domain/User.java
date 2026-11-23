package ru.tsyplakov.airlines.domain;

public class User extends BaseEntity {

    private String fullName;
    private String email;
    private String passwordHash;
    private UserRole role;

    public User(Long id, String fullName, String email, String passwordHash) {
        super(id);
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = UserRole.PASSENGER;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
