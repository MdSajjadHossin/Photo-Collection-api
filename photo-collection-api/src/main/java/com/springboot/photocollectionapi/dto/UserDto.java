package com.springboot.photocollectionapi.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;
    private String userName;
    private String email;
    private String password;
    private String about;
}
