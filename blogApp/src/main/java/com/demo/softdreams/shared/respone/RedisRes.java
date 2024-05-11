package com.demo.softdreams.shared.respone;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedisRes {
    @NotEmpty(message = "Không được để trống !!")
    private String username;
    @NotEmpty(message = "Không được để trống")
    private String password;
    @NotEmpty(message = "Không được để trống !!")
    private Long id;
}
