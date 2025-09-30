package com.userAuth.UserAuthentication.Domain.Users;

public record RegisterDTO(String login, String password, UserRole role) {
}
