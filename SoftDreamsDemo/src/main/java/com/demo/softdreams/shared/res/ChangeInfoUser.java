package com.demo.softdreams.shared.res;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeInfoUser{
    private Long id;
    @NotBlank(message = "This filed not empty")
    private String name;
    @NotBlank(message = "This filed not empty")
    private String address;
    @NotBlank(message = "This filed not empty")
    private Date birthday;
    @NotBlank(message = "This filed not empty")
    private String gender;
    private String avatar;
    @Pattern(regexp = "^0\\d{8,10}$", message = "NumberPhone not vaild")
    @Size(min = 9, max = 11, message = "Numberphone have Size 9 -> 11")
    private String msisdn;

}