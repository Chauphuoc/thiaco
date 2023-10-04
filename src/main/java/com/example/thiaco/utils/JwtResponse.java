package com.example.thiaco.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
    //    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String roles;

    public JwtResponse(String accessToken, String username, String roles) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }
}