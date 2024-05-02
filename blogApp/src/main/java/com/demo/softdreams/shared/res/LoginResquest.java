package com.demo.softdreams.shared.res;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResquest {
    @NotEmpty(message = "Không được để trống !!")
    private String username;
    @NotEmpty(message = "Không được để trống")
    private String password;
}
