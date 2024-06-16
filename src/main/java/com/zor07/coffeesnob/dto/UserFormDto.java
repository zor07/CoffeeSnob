package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.Role;
import com.zor07.coffeesnob.entity.User;

public record UserFormDto(
        Long id,
        String username,
        String password,
        String confirmPassword,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        RoleDto role
) {

    public User toEntity() {
        Role role = new Role();
        Long roleId = this.role != null
                ? this.role.id()
                : null;
        String roleName = this.role != null
                ? this.role.name()
                : null;
        role.setId(roleId);
        role.setName(roleName);

        User user = new User();
        user.setId(this.id);
        user.setRole(role);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        return user;
    }

    public static UserFormDto toUserFormDto(User user) {
        return new UserFormDto(
                user.getId(),
                user.getUsername(),
                "",
                "",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                RoleDto.fromEntity(user.getRole())
        );
    }


}
