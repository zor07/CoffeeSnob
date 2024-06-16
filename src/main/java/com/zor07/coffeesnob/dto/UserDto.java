package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.Role;
import com.zor07.coffeesnob.entity.User;

public record UserDto(
        Long id,
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        RoleDto role
) {

    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role.toEntity());
        return user;
    }

    public static UserDto toDto(User user) {
        Role role = user.getRole() != null ? user.getRole() : new Role();
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                RoleDto.fromEntity(role));
    }

}
