package com.example.thiaco.dto;

import com.example.thiaco.model.user.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResDTO {
    private Long id;

    private String username;

    private List<RoleDTO> roleDTOs;
}
