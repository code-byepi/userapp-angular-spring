package com.userapp.backend.models;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class UserRequest implements iUser {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotEmpty
    @Email
    private String email;

    private boolean admin;

    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotBlank String getLastname() {
        return lastname;
    }

    public void setLastname(@NotBlank String lastname) {
        this.lastname = lastname;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank @Size(min = 6, max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(min = 6, max = 20) String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
