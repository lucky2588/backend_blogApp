package com.demo.softdreams.shared.res;

import com.demo.softdreams.core.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRes {
    @JsonProperty("auth")
    private UserDTO userDTO;
    private String token;
    @JsonProperty("isAuthenticated")
    private boolean isAuthenticated;
}
