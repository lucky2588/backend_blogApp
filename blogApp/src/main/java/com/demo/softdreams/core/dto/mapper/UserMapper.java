package com.demo.softdreams.core.dto.mapper;

import com.demo.softdreams.core.dto.UserDTO;
import com.demo.softdreams.core.entites.User;

public class UserMapper {
    public static UserDTO toUserDto(User user) {
        UserDTO userDto = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getAvatar(),
                user.getRoles()
        );
        return userDto;
    }
}
