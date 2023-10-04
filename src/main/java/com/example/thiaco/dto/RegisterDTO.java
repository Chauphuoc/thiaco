package com.example.thiaco.dto;

import com.example.thiaco.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class RegisterDTO {
    private String username;
    private String password;
    private String codeRole;

    public User toUser(){
        return new User()
                .setId(null)
                .setUsername(username)
                .setPassword(password);
    }

}