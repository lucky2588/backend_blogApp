package com.demo.softdreams.core.dto;

import com.demo.softdreams.core.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String avatar;
    private String gender;
    private String address;
    private Date birthday;
    private String msisdn;
}
