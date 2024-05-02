package com.demo.softdreams.shared.res;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RegisterResquest{
    @NotBlank(message = "This filed not empty")
    private String name;
    @NotBlank(message = "This filed not empty")
    private String username;
    @NotBlank(message = "This filed not empty")
    private String address;

    private Date birthday;
    @NotBlank(message = "This filed not empty")
    private String email;
    @NotBlank(message = "This filed not empty")
    private String gender;
    @Size(min = 6, max = 20, message = " Password not vail !! ")
    private String password;
    @Pattern(regexp = "^0\\d{8,10}$", message = "NumberPhone not vail")
    @Size(min = 9, max = 11, message = "NumberPhone Not Enough Length")
    private String msisdn;
    @NotBlank(message = "This filed not empty")
    private String reTypePassword;
}
