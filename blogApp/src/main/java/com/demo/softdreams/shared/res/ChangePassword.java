package com.demo.softdreams.shared.res;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword{
    @NotBlank(message = "This filed not empty")
    private Long id;
    @NotBlank(message = "This filed not empty")
    @Size(min = 6, max = 20, message = "Password have six char up to  !!")
    private String passwordNew;
    @NotBlank(message = "This filed not empty")
    @Size(min = 6, max = 20, message = "Password have six char up to  !!")
    private String passwordOld;
    @Size(min = 6, max = 20, message = "Password have six char up to  !!")
    @NotBlank(message = "This filed not empty")
    private String passRepart;

}